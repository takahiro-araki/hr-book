package com.example.demo.contoroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import org.apache.tomcat.util.codec.binary.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.BaseSkill;
import com.example.demo.domain.CommonSkill;
import com.example.demo.domain.Human;
import com.example.demo.domain.PreHumanBaseSkill;
import com.example.demo.domain.PreHumanCommonSkill;
import com.example.demo.domain.PreHumanSubSkill;
import com.example.demo.domain.SubSkill;
import com.example.demo.form.InputSkillForm;
import com.example.demo.service.InputSkillService;
import com.example.demo.service.OrderConfirmationService;

/**
 * スキル申請確認画面に関するコントローラ.
 * 
 * @author atsushi
 *
 */
@Controller
@RequestMapping("")
public class OrderConfirmationController {

	@ModelAttribute
	public InputSkillForm setUpForm() {
		return new InputSkillForm();
	}

	@Autowired
	private OrderConfirmationService orderConfirmationService;

	@Autowired
	private InputSkillService inputSkillService;

	@RequestMapping("/order-conf")
	public String showOrderConfirmation(@Validated InputSkillForm form, BindingResult result, Model model)
			throws ParseException, UnsupportedEncodingException, IOException {
		Human human = new Human();
		human.setEmpId(form.getIntEmpId());
		human.setHumanName(form.getHumanName());
		human.setAssignCompanyName(form.getAssignCompanyName());
		human.setJoinDate(form.getDateJoinData());
		// 画像のバリデーションチェックのメソッドを呼び出す
		checkImage(form.getIconImg().getBytes(), form.getIconImg().getOriginalFilename(), result);
		human.setIconImg(changeBase64(form.getIconImg()));

		model.addAttribute("human", human);

		// baseSkillScoreのバリデーションチェック
		for (String score : form.getBaseSkillScores()) {
			String pattern = "^[1-9]{1,2}$|^100$|^0$";
			if (!score.matches(pattern)) {
				result.rejectValue("baseSkillScores", "", "半角数字0~100で記入ください");
				break;
			}
		}
		// subSkillIdのバリデーションチェック
		if (form.getSubSkillIds() == null) {
			result.rejectValue("subSkillIds", "", "1つ以上選択してください");
		}
		// result.hasErrorメソッド
		if (result.hasErrors()) {
			model.addAttribute("baseSkillList", inputSkillService.findAllBaseSkill());
			model.addAttribute("commonSkillList", inputSkillService.findAllCommonSkill());
			model.addAttribute("subSkillList", inputSkillService.findAllSubSkill());
			return "regist";
		}

		List<BaseSkill> baseSkills = new ArrayList<>();
		baseSkills = orderConfirmationService.findAllBaseSkill();
		List<PreHumanBaseSkill> preHumanBaseSkillList = new ArrayList<>();
		for (int i = 0; i < form.getBaseSkillIds().size(); i++) {
			PreHumanBaseSkill preHumanBaseSkill = new PreHumanBaseSkill();
			preHumanBaseSkill.setBaseSkillId(Integer.parseInt(form.getBaseSkillIds().get(i)));
			preHumanBaseSkill.setBaseSkillScore(Integer.parseInt(form.getBaseSkillScores().get(i)));
			preHumanBaseSkill.setBaseSkill(baseSkills.get(i));
			preHumanBaseSkillList.add(preHumanBaseSkill);
		}
		List<CommonSkill> commonSkills = new ArrayList<>();
		commonSkills = orderConfirmationService.findAllCommonSkill();
		List<PreHumanCommonSkill> preHumanCommonSkillList = new ArrayList<>();
		for (int i = 0; i < form.getCommonSkillIds().size(); i++) {
			PreHumanCommonSkill preHumanCommonSkill = new PreHumanCommonSkill();
			preHumanCommonSkill.setCommonSkillId(Integer.parseInt(form.getCommonSkillIds().get(i)));
			preHumanCommonSkill.setCommonSkillScore(Integer.parseInt(form.getCommonSkillScores().get(i)));
			preHumanCommonSkill.setCommonSkill(commonSkills.get(i));
			preHumanCommonSkillList.add(preHumanCommonSkill);
		}
		List<SubSkill> subSkills = new ArrayList<>();
		subSkills = orderConfirmationService.findAllSubSkill();
		List<PreHumanSubSkill> preHumanSubSkillList = new ArrayList<>();
		for (int i = 0; i < form.getSubSkillIds().size(); i++) {
			PreHumanSubSkill preHumanSubSkill = new PreHumanSubSkill();
			preHumanSubSkill.setSubSkillId(Integer.parseInt(form.getSubSkillIds().get(i)));
			preHumanSubSkill.setSubSkill(subSkills.get(i));
			preHumanSubSkillList.add(preHumanSubSkill);
		}

		model.addAttribute("preHumanBaseSkillList", preHumanBaseSkillList);
		model.addAttribute("preHumanCommonSkillList", preHumanCommonSkillList);
		model.addAttribute("preHumanSubSkillList", preHumanSubSkillList);
		model.addAttribute("form", form);

		return "order-confrimation";
	}

	/**
	 * スキル登録をするメソッド.
	 * 
	 * @param スキル情報
	 * @param エラーメッセージ格納変数
	 * @return 社員一覧
	 */
	@RequestMapping("/insert-skills")
	public String insertSkills(@Validated InputSkillForm form, byte[] iconImageByte, String iconImageName,
			BindingResult result, Model model) {

		// 画像のバリデーションチェックのメソッドを呼び出す
		checkImage(iconImageByte, iconImageName, result);
		// result.hasErrorメソッド
		if (result.hasErrors()) {
			model.addAttribute("baseSkillList", inputSkillService.findAllBaseSkill());
			model.addAttribute("commonSkillList", inputSkillService.findAllCommonSkill());
			model.addAttribute("subSkillList", inputSkillService.findAllSubSkill());
			return "regist";
		}
		try {
			// 社員番号があればhumanを追加する処理
			if(form.getEmpId() != null) {
				orderConfirmationService.insertHuman(form, iconImageByte, iconImageName);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/list";
	}

	/**
	 * 画像ファイルのバリデーションチェックメソッド.
	 * 
	 * @param 入力したスキル情報
	 * @param エラーメッセージ格納変数
	 */
	public void checkImage(byte[] iconImageByte, String iconImageName, BindingResult result) {
		String fileExtension = null;
		try {
			fileExtension = extractExtension(iconImageName);
			extractExtension(iconImageName);
			if (!"jpg".equals(fileExtension) && !"png".equals(fileExtension)) {
				result.rejectValue("iconImg", "", "拡張子が.pngまたは.jpgのファイルを選択してください");
			}
		} catch (FileNotFoundException e) {
			result.rejectValue("iconImg", "", "拡張子が.pngまたは.jpgのファイルを選択してください");

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 拡張子を取り出すメソッド.
	 * 
	 * @return 画像ファイルの拡張子
	 * @throws FileNotFoundException
	 */
	public String extractExtension(String imageFileName) throws FileNotFoundException {
		if (imageFileName == null) {
			throw new FileNotFoundException();
		}
		int point = imageFileName.lastIndexOf(".");
		if (point == -1) {
			throw new FileNotFoundException();
		}
		return imageFileName.substring(point + 1);
	}

	/**
	 * 画像をBase64にエンコードするメソッド.
	 * 
	 * @param image
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public String changeBase64(MultipartFile image) throws UnsupportedEncodingException, IOException {
		StringBuffer data = new StringBuffer();
		String base64 = new String(Base64.encodeBase64(image.getBytes()), "ASCII");
		data.append("data:image/jpeg;base64,");
		data.append(base64);
		return data.toString();
	}
}

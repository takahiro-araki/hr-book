package com.example.demo.contoroller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.form.InputSkillForm;
import com.example.demo.service.InputSkillService;

/**
 * @author yuma.watanabe
 *
 */
@RequestMapping("")
@Controller
public class InputSkillController {

	@Autowired
	private InputSkillService inputSkillService;

	@ModelAttribute
	public InputSkillForm setUpForm() {
		return new InputSkillForm();
	}

	/**
	 * 入力画面を表示するメソッド.
	 * 
	 * @param model
	 * @return 入力画面
	 */
	@RequestMapping("showSkillForm")
	public String showSkillForm(Model model) {
		model.addAttribute("baseSkillList", inputSkillService.findAllBaseSkill());
		model.addAttribute("commonSkillList", inputSkillService.findAllCommonSkill());
		model.addAttribute("subSkillList", inputSkillService.findAllSubSkill());
		return "regist";
	}

	/**
	 * スキル登録するメソッド.
	 * 
	 * @param スキル情報
	 * @param エラーメッセージ格納変数
	 * @return おそらく個別スキル画面.
	 */
	@RequestMapping("insertSkills")
	public String insertSkills(@Validated InputSkillForm form, BindingResult result, Model model) {
		// 画像のバリデーションチェックのメソッドを呼び出す
		checkImage(form, result);
		// baseSkillScoreのバリデーションチェック
		for (String score : form.getBaseSkillScores()) {
			String pattern = "^[1-9]{1,2}$|^100$|^0$";
			if (!score.matches(pattern)) {
				result.rejectValue("baseSkillScores", "", "半角数字0~100で記入ください");
				break;
			}
		}
		if (form.getSubSkillIds() == null) {
			result.rejectValue("subSkillIds", "", "1つ以上選択してください");
		}
		// subSkillIdのバリデーションチェック

		// result.hasErrorメソッド
		if (result.hasErrors()) {
			model.addAttribute("baseSkillList", inputSkillService.findAllBaseSkill());
			model.addAttribute("commonSkillList", inputSkillService.findAllCommonSkill());
			model.addAttribute("subSkillList", inputSkillService.findAllSubSkill());
			return "regist";
		}
		try {
			inputSkillService.insertHuman(form);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "regist";
	}

	/**
	 * 画像ファイルのバリデーションチェックメソッド.
	 * 
	 * @param 入力したスキル情報.
	 * @param エラーメッセージ格納変数
	 */
	public void checkImage(InputSkillForm form, BindingResult result) {
		MultipartFile imageFile = form.getIconImage();
		String fileExtension = null;
		try {
			fileExtension = extractExtension(imageFile.getOriginalFilename());
			extractExtension(imageFile.getOriginalFilename());
			if (!"jpg".equals(fileExtension) && !"png".equals(fileExtension)) {
				result.rejectValue("iconImage", "", "拡張子が.pngまたは.jpgのファイルを選択してください");
			}
		} catch (FileNotFoundException e) {
			result.rejectValue("iconImage", "", "拡張子が.pngまたは.jpgのファイルを選択してください");

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
}

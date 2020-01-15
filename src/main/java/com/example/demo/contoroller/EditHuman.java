package com.example.demo.contoroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.BaseSkill;
import com.example.demo.domain.Human;
import com.example.demo.domain.PreHumanSubSkill;
import com.example.demo.form.InputSkillForm;
import com.example.demo.service.InputSkillService;
import com.example.demo.service.ShowHumanService;

/**エンジニアのhuman情報を編集するコントローラー.
 * @author takahiro.araki
 *
 */
@Controller
@RequestMapping("")
public class EditHuman {
	
	@Autowired
	private InputSkillService inputSkillService;

	@Autowired
	private ShowHumanService humanService;

	@ModelAttribute
	public InputSkillForm setUpForm() {
		return new InputSkillForm();
	}
	
	
	/**
	 * 編集画面を表示するメソッド
	 * 
	 * @param model,human情報のID
	 * @return 編集画面
	 */
	@RequestMapping("showEditForm")
	public String showSkillForm(Model model, Integer empId) {
			List<BaseSkill> baseSkillList = inputSkillService.findAllBaseSkill();
			Human user = null;
			List<Integer> selectOptions = new ArrayList<>();
			for (int i = 1; i <= 5; i++) {
				selectOptions.add(i);
			}
			model.addAttribute("selectOptions", selectOptions);
			user = humanService.load(empId);
			Map<Integer, String> valueMap = new HashMap<>();
			for (int i = 1; i <= baseSkillList.size(); i++) {
				valueMap.put(i, "off");
			}
			for (PreHumanSubSkill subSkill : user.getSubSkills()) {
				for (int i = 1; i <= baseSkillList.size(); i++) {
					if (subSkill.getSubSkillId() == i) {
						valueMap.put(i, "on");
					} else if (subSkill.getSubSkillId() == null) {
						break;
					}
				}
			}
			model.addAttribute("valueMap", valueMap);
			model.addAttribute("user", user);
			model.addAttribute("baseSkillList", baseSkillList);
			model.addAttribute("commonSkillList", inputSkillService.findAllCommonSkill());
			model.addAttribute("subSkillList", inputSkillService.findAllSubSkill());
			return "edit";
	}

	
}

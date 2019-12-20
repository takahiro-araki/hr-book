package com.example.demo.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("showSkillForm")
	public String showSkillForm(Model model) {
		model.addAttribute("baseSkillList", inputSkillService.findAllBaseSkill());
		model.addAttribute("commonSkillList", inputSkillService.findAllCommonSkill());
		model.addAttribute("subSkillList", inputSkillService.findAllSubSkill());
		return "regist";
	}
	@RequestMapping("insertSkills")
	public String insertSkills(InputSkillForm form) {
		System.out.println(form);
		return "regist";
	}
}

package com.example.demo.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.BaseSkillService;
import com.example.demo.service.CommonSkillService;
import com.example.demo.service.SubSkillService;

/**
 * @author yuma.watanabe
 *
 */
@RequestMapping("")
@Controller
public class InputSkillController {

	@Autowired
	private BaseSkillService baseSkillServece;

	@Autowired
	private CommonSkillService commonSkillService;

	@Autowired
	private SubSkillService subSkillService;

	@RequestMapping("showSkillForm")
	public String showSkillForm(Model model) {
		model.addAttribute("baseSkillList", baseSkillServece.findAll());
		model.addAttribute("commonSkillList", commonSkillService.findAll());
		model.addAttribute("subSkillList", subSkillService.findAll());
		return "regist";
	}
}

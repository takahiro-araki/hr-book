package com.example.demo.contoroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.BaseSkill;
import com.example.demo.service.BaseSkillService;

/**
 * @author yuma.watanabe
 *
 */
@RequestMapping("")
@Controller
public class InputSkillController {

	@Autowired
	private BaseSkillService baseSkillServece;

	
	@RequestMapping("showSkillForm")
	public String showSkillForm(Model model) {
		System.out.println("AAA");
		List<BaseSkill> baseSkillList = baseSkillServece.findAll();
		model.addAttribute("baseSkillList", baseSkillList);
		return "regist";
	}
}

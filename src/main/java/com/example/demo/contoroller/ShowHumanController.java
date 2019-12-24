package com.example.demo.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ShowHumanService;

/**
 * エンジニアの情報を操作するコントローラ.
 * 
 * @author morishimashun
 *
 */
@Controller
@RequestMapping("/emp-list")
public class ShowHumanController {

	@Autowired
	private ShowHumanService humanService;

	/**
	 * エンジニアの個別のスキル詳細ページを表示するメソッド.
	 * 
	 * @return スキル詳細ページ
	 */
	@RequestMapping("/detail")
	public String showDetail(Model model, Integer humanId) {
		if (humanId == null) humanId = 1;
		model.addAttribute("human", humanService.load(humanId));
		return "humanDetail";
	}

}
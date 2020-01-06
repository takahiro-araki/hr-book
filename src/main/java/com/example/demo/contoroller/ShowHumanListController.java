package com.example.demo.contoroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Human;
import com.example.demo.domain.LoginUser;
import com.example.demo.domain.PreHumanBaseSkill;
import com.example.demo.form.ShowHumanListForm;
import com.example.demo.service.ShowHumanListService;

/**
 * エンジニア一覧画面を表示するコントローラークラス.
 * 
 * @author takahiro.araki
 *
 */
@Controller
@RequestMapping("/list")
public class ShowHumanListController {

	@Autowired
	ShowHumanListService showHumanListService;

	private static final int VIEW_SIZE = 2;

	/**
	 * エンジニアを一覧表示するメソッド.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String showList(@AuthenticationPrincipal LoginUser loginUser, ShowHumanListForm form, Model model) {
		if (form.getPage() == null) {
			form.setPage(1);
		}
		if (form.getName() == null) {
			form.setName("");
		}
		if (form.getOrder() == null) {
			form.setOrder("");
		}
		List<Human> humanList = showHumanListService.showList(form, loginUser.getUser().getUserRole(),
				loginUser.getUser().getUserId());
		// 検証用。終わったら削除
		/*
		 * for (Human human : humanList) {
		 * System.out.println("-----------Human情報-----------------");
		 * System.out.println("名前：" + human.getHumanName()); System.out.println("入社日：" +
		 * human.getJoinDate()); System.out.println("申請状況：" + human.getOrderStatus());
		 * System.out.println("-----------スキル情報-----------------"); for
		 * (PreHumanBaseSkill baseSkill : human.getBaseSkills()) {
		 * System.out.println("スキル名：" + baseSkill.getBaseSkill().getBaseSkillName());
		 * System.out.println("スキルスコア：" + baseSkill.getBaseSkillScore()); } }
		 */
		if (loginUser.getUser().getUserRole() == 3) {
			Human user = null;
			int num = -1;
			for (int i = 0; i < humanList.size(); i++) {
				if (humanList.get(i).getUserId() == 1) {
					user = new Human();
					BeanUtils.copyProperties(humanList.get(i), user);
					num = i;
				}

			}
			humanList.remove(num);
			model.addAttribute("user", user);
		}
		Page<Human> humanPage = showHumanListService.showListPaging(form.getPage(), VIEW_SIZE, humanList);
		List<Integer> pageNum = calcPageNum(model, humanPage);
		model.addAttribute("name", form.getName());
		model.addAttribute("order", form.getOrder());
		model.addAttribute("humanPage", humanPage);
		model.addAttribute("pageNumbers", pageNum);
		return "emp_list";
	}

	/**総ページ数を計算するメソッド.
	 * @param model
	 * @param 1ページあたりのhuman情報
	 * @return　総ページ数
	 */
	private List<Integer> calcPageNum(Model model, Page<Human> humanPage) {
		int totalPages = humanPage.getTotalPages();
		List<Integer> pageNum = null;
		if (totalPages > 0) {
			pageNum = new ArrayList<Integer>();
			for (int i = 1; i <= totalPages; i++) {
				pageNum.add(i);
			}
		}
		return pageNum;
	}

}

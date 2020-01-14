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

import com.example.demo.common.UserRole;
import com.example.demo.domain.Human;
import com.example.demo.domain.LoginUser;
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
		int num = -1;
		UserRole role=new UserRole();
		if (loginUser.getUser().getUserRole() ==role.ENGINEER ) {			
			Human user = null;
			
			for (int i = 0; i < humanList.size(); i++) {
				if (humanList.get(i).getUserId() == loginUser.getUser().getUserId()) {
					user = new Human();
					BeanUtils.copyProperties(humanList.get(i), user);
					num = i;
				}
			}
			model.addAttribute("user", user);
		}
		if(num!=-1) {
			humanList.remove(num);	
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

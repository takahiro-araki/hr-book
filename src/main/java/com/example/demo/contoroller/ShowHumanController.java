package com.example.demo.contoroller;

import java.text.ParseException;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Human;
import com.example.demo.domain.LoginUser;
import com.example.demo.service.ShowHumanService;
import com.example.demo.service.UpdateOrderService;

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
	@Autowired
	private UpdateOrderService updateOrderService;
	@Autowired
	private HttpSession session;

	/**
	 * エンジニアの個別のスキル詳細ページを表示するメソッド.
	 * 
	 * @return スキル詳細ページ
	 */
	@RequestMapping("/detail")
	public String showDetail(Integer humanId, @AuthenticationPrincipal LoginUser loginUser) {
		if (humanId == null && Objects.isNull(session.getAttribute("human"))) {
			humanId = 1;
		} else if (humanId == null && Objects.nonNull(session.getAttribute("human"))) {
			humanId = ((Human) session.getAttribute("human")).getHumanId();
		}
		Human human = humanService.load(humanId);
		session.setAttribute("human", human);
		return "humanDetail";
	}

	/**
	 * 申請されたスキルを承認する.
	 * 
	 * @param loginUser
	 * @return スキル詳細ページ
	 */
	@RequestMapping("/detail/authen")
	public String showDetail(@AuthenticationPrincipal LoginUser loginUser) {
		if (loginUser.getUser().getUserRole() != 0) {
			return "UnauthorizedAccess";
		}
		Human human = (Human) session.getAttribute("human");
		try {
			updateOrderService.authenOrder(loginUser, human);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/emp-list/detail";
	}

}

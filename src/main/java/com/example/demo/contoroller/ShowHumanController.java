package com.example.demo.contoroller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Human;
import com.example.demo.domain.LoginUser;
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
	@Autowired
	private HttpSession session;

	/**
	 * エンジニアの個別のスキル詳細ページを表示するメソッド.
	 * 
	 * @return スキル詳細ページ
	 */
	@RequestMapping("/detail")
	public String showDetail(Integer humanId, @AuthenticationPrincipal LoginUser loginUser) {
		if (humanId == null)
			humanId = 1;
		Human human = humanService.load(humanId);
		if (Objects.nonNull(session.getAttribute("human"))) {
			session.removeAttribute("human");
		}
		session.setAttribute("human", human);
		return "humanDetail";
	}
	
}

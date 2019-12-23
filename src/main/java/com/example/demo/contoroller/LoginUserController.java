package com.example.demo.contoroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author atsushi
 *
 * ログインを操作するコントローラ.
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginUserController {

	@RequestMapping("")
	public String toLogin(Model model, String error) {

		System.err.println("login error:" + error);
		if (error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
		}
		return "login";
	}
}

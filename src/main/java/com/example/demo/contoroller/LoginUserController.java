package com.example.demo.contoroller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.User;
import com.example.demo.form.InsertUserForm;
import com.example.demo.service.LoginUserService;

/**
 * @author atsushi
 *
 *         ログインを操作するコントローラ.
 * 
 */
@Controller
@RequestMapping("/user")
public class LoginUserController {

	@Autowired
	private LoginUserService loginUserService;

	@ModelAttribute
	public InsertUserForm setUpInsertUserForm() {
		return new InsertUserForm();
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/toLogin")
	public String toLogin(Model model, String error) {
		System.err.println("login error:" + error);
		if (error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
		}
		return "login";
	}

	/**
	 * ユーザ登録画面を出力.
	 * 
	 * @return
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "register_user";
	}

	/**
	 * ユーザ登録.
	 * 
	 * @param form   ユーザ情報登録用フォーム
	 * @param result エラーメッセージを格納
	 * @return ログイン画面へリダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertUserForm form, BindingResult result) {
		// パスワード確認
		if (!form.getPassword().equals(form.getConfirmationPassword())) {
			result.rejectValue("password", "", "パスワードが一致していません");
			result.rejectValue("confirmationPassword", "", "");
		}

		// メールアドレスが重複している場合の処理
		User existUser = loginUserService.findByMailAddress(form.getMailAddress());
		if (existUser != null) {
			result.rejectValue("email", "", "そのメールアドレスは既に登録されています");
		}

		// もしエラーが一つでもあれば入力画面に戻る
		if (result.hasErrors()) {
			return toInsert();
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setEmpId(Integer.parseInt(form.getEmpId()));
		user.setUserRole(Integer.parseInt(form.getUserRole()));

		// 登録処理
		loginUserService.insert(user);
		return "redirect:/user/toLogin";
	}

}

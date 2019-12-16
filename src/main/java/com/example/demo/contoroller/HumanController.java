package com.example.demo.contoroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * エンジニアの情報を操作するコントローラ.
 * 
 * @author morishimashun
 *
 */
@Controller
@RequestMapping("/emp-list")
public class HumanController {

	/**
	 * エンジニアの個別のスキル詳細ページを表示するメソッド.
	 * 
	 * @return スキル詳細ページ
	 */
	@RequestMapping("/detail")
	public String showDetail() {

		return "test";

	}

}

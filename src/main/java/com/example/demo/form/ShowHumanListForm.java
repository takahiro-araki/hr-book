package com.example.demo.form;

/**
 * 従業員一覧画面の検索フォームクラス.
 * 
 * @author takahiro.araki
 *
 */
public class ShowHumanListForm {

	/** セレクトタグからの検索オーダー */
	private String order;

	/** 現ページ数 */
	private Integer page;
	/** あいまい検索の名前 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * いずれかのフィールドに値が入っていたらtureを返すメソッド.
	 * 
	 * @return boolean値
	 */
	public boolean search() {
		if ((this.order == null && this.name == null)||(this.order==""&&this.name=="")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "ShowHumanListForm [order=" + order + ", page=" + page + ", name=" + name + "]";
	}

}

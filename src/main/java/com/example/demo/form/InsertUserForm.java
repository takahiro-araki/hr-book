package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * ユーザ情報登録時に使用するフォーム.
 * 
 * @author shun.m
 *
 */
public class InsertUserForm {

	/** 従業員ID */
	@Pattern(message = "入力形式が不正です", regexp = "^[0-9]{4}$")
	private String empId;

	/** ユーザ名 */
	@NotBlank(message = "お名前を入力してください")
	private String userName;

	/** メールアドレス */
	@Email(message = "アドレスが不正です")
	@NotBlank(message = "メールアドレスを入力してください")
	private String mailAddress;

	/** パスワード */
	@NotBlank(message = "パスワードを入力してください")
	private String password;

	/** 確認パスワード */
	@NotBlank(message = "確認用パスワードを入力してください")
	private String confirmationPassword;

	/** 権限 */
	@Pattern(message = "入力形式が不正です", regexp = "^[0-2]{1}$")
	private String userRole;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "InsertUserForm [empId=" + empId + ", userName=" + userName + ", mailAddress=" + mailAddress
				+ ", password=" + password + ", confirmationPassword=" + confirmationPassword + ", userRole=" + userRole
				+ "]";
	}

	

}

package com.example.demo.domain;

/**
 * ユーザーの基本情報.
 * 
 * @author morishimashun
 *
 */
public class User {

	/** ユーザーID */
	private Integer userId;
	/** 従業員ID */
	private Integer empId;
	/** ユーザー名 */
	private String name;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;
	/** ユーザーの職種 */
	private Integer userRole;
	/** ユーザーの状態 */
	private Integer actStatus;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	public Integer getActStatus() {
		return actStatus;
	}
	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", empId=" + empId + ", name=" + name + ", mailAddress=" + mailAddress
				+ ", password=" + password + ", userRole=" + userRole + ", actStatus=" + actStatus + "]";
	}
}

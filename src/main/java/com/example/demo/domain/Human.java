package com.example.demo.domain;

import java.util.Date;
import java.util.List;

/**
 * エンジニアのドメインクラス.
 * 
 * @author morishimashun
 *
 */
public class Human {

	/** 従業員ID */
	private Integer empId;
	/** エンジニアの名前 */
	private String humanName;
	/** 入社日 */
	private Date joinDate;
	/** アサイン先 */
	private String assignCompanyName;
	/** アイコン画像 */
	private String icon_img;
	/** ベーススキル一覧 */
	private List<PreHumanBaseSkill> baseSkills;
	/** サブスキル一覧 */
	private List<PreHumanSubSkill> subSkills;
	/** コモンスキル一覧 */
	private List<PreHumanCommonSkill> commonSkills;
	/** 申請状況 */
	private Integer orderStatus;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getHumanName() {
		return humanName;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getAssignCompanyName() {
		return assignCompanyName;
	}

	public void setAssignCompanyName(String assignCompanyName) {
		this.assignCompanyName = assignCompanyName;
	}

	public String getIcon_img() {
		return icon_img;
	}

	public void setIcon_img(String icon_img) {
		this.icon_img = icon_img;
	}

	public List<PreHumanBaseSkill> getBaseSkills() {
		return baseSkills;
	}

	public void setBaseSkills(List<PreHumanBaseSkill> baseSkills) {
		this.baseSkills = baseSkills;
	}

	public List<PreHumanSubSkill> getSubSkills() {
		return subSkills;
	}

	public void setSubSkills(List<PreHumanSubSkill> subSkills) {
		this.subSkills = subSkills;
	}

	public List<PreHumanCommonSkill> getCommonSkills() {
		return commonSkills;
	}

	public void setCommonSkills(List<PreHumanCommonSkill> commonSkills) {
		this.commonSkills = commonSkills;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Human [empId=" + empId + ", humanName=" + humanName + ", joinDate=" + joinDate + ", assignCompanyName="
				+ assignCompanyName + ", icon_img=" + icon_img + ", baseSkills=" + baseSkills + ", subSkills="
				+ subSkills + ", commonSkills=" + commonSkills + ", orderStatus=" + orderStatus + "]";
	}

}

package com.example.demo.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * エンジニアのドメインクラス.
 * 
 * @author morishimashun
 *
 */
public class Human {

	/** エンジニアの名前 */
	private String humanName;
	/** 入社日 */
	private LocalDate joinDate;
	/** アイコン画像 */
	private String icon_img;
	/** ベーススキル一覧 */
	private List<BaseSkill> baseSkills;
	/** サブスキル一覧 */
	private List<SubSkill> subSukills;
	/** コモンスキル一覧 */
	private List<CommonSkill> commonSkills;
	/** 申請状況 */
	private Integer orderStatus;

	public String getHumanName() {
		return humanName;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public String getIcon_img() {
		return icon_img;
	}

	public void setIcon_img(String icon_img) {
		this.icon_img = icon_img;
	}

	public List<BaseSkill> getBaseSkills() {
		return baseSkills;
	}

	public void setBaseSkills(List<BaseSkill> baseSkills) {
		this.baseSkills = baseSkills;
	}

	public List<SubSkill> getSubSukills() {
		return subSukills;
	}

	public void setSubSukills(List<SubSkill> subSukills) {
		this.subSukills = subSukills;
	}

	public List<CommonSkill> getCommonSkills() {
		return commonSkills;
	}

	public void setCommonSkills(List<CommonSkill> commonSkills) {
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
		return "Human [humanName=" + humanName + ", joinDate=" + joinDate + ", icon_img=" + icon_img + ", baseSkills="
				+ baseSkills + ", subSukills=" + subSukills + ", commonSkills=" + commonSkills + ", orderStatus="
				+ orderStatus + "]";
	}

}

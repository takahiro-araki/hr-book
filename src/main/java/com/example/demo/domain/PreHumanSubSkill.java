package com.example.demo.domain;

import java.time.LocalDate;

/**
 * @author yuma.watanabe
 *
 */
public class PreHumanSubSkill {
	private Integer preHumanSubSkillId;
	private Integer orderId;
	private Integer subSkillId;
	private Integer actStatus;
	private String register;
	private LocalDate registDate;
	private String renewer;
	private LocalDate renewDate;

	public Integer getPreHumanSubSkillId() {
		return preHumanSubSkillId;
	}

	public void setPreHumanSubSkillId(Integer preHumanSubSkillId) {
		this.preHumanSubSkillId = preHumanSubSkillId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getSubSkillId() {
		return subSkillId;
	}

	public void setSubSkillId(Integer subSkillId) {
		this.subSkillId = subSkillId;
	}

	public Integer getActStatus() {
		return actStatus;
	}

	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public LocalDate getRegistDate() {
		return registDate;
	}

	public void setRegistDate(LocalDate registDate) {
		this.registDate = registDate;
	}

	public String getRenewer() {
		return renewer;
	}

	public void setRenewer(String renewer) {
		this.renewer = renewer;
	}

	public LocalDate getRenewDate() {
		return renewDate;
	}

	public void setRenewDate(LocalDate renewDate) {
		this.renewDate = renewDate;
	}

	@Override
	public String toString() {
		return "PreHumanSubSkill [preHumanSubSkillId=" + preHumanSubSkillId + ", orderId=" + orderId + ", subSkillId="
				+ subSkillId + ", actStatus=" + actStatus + ", register=" + register + ", registDate=" + registDate
				+ ", renewer=" + renewer + ", renewDate=" + renewDate + "]";
	}

}

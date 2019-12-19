package com.example.demo.domain;

import java.time.LocalDate;

/**
 * @author yuma.watanabe
 * 
 */
public class PreHumanBaseSkill {
	private Integer preHumanBaseSkillId;
	private Integer orderId;
	private Integer baseSkillId;
	private Integer actStatus;
	private String register;
	private LocalDate registDate;
	private String renewer;
	private LocalDate renewDate;

	public Integer getPreHumanBaseSkillId() {
		return preHumanBaseSkillId;
	}

	public void setPreHumanBaseSkillId(Integer preHumanBaseSkillId) {
		this.preHumanBaseSkillId = preHumanBaseSkillId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getBaseSkillId() {
		return baseSkillId;
	}

	public void setBaseSkillId(Integer baseSkillId) {
		this.baseSkillId = baseSkillId;
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
		return "PreHumanBaseSkill [preHumanBaseSkillId=" + preHumanBaseSkillId + ", orderId=" + orderId
				+ ", baseSkillId=" + baseSkillId + ", actStatus=" + actStatus + ", register=" + register
				+ ", registDate=" + registDate + ", renewer=" + renewer + ", renewDate=" + renewDate + "]";
	}

}

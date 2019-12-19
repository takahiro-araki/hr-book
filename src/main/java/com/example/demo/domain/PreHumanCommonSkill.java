package com.example.demo.domain;

import java.time.LocalDate;

/**
 * @author yuma.watanabe
 *
 */
public class PreHumanCommonSkill {
	private Integer humanCommonSkillId;
	private Integer orderId;
	private Integer commonSkillId;
	private Integer commonSkillScore;
	private Integer actStatus;
	private String register;
	private LocalDate registDate;
	private String renewer;
	private LocalDate renewDate;

	public Integer getHumanCommonSkillId() {
		return humanCommonSkillId;
	}

	public void setHumanCommonSkillId(Integer humanCommonSkillId) {
		this.humanCommonSkillId = humanCommonSkillId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCommonSkillId() {
		return commonSkillId;
	}

	public void setCommonSkillId(Integer commonSkillId) {
		this.commonSkillId = commonSkillId;
	}

	public Integer getCommonSkillScore() {
		return commonSkillScore;
	}

	public void setCommonSkillScore(Integer commonSkillScore) {
		this.commonSkillScore = commonSkillScore;
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
		return "PreHumanCommonSkill [humanCommonSkillId=" + humanCommonSkillId + ", orderId=" + orderId
				+ ", commonSkillId=" + commonSkillId + ", commonSkillScore=" + commonSkillScore + ", actStatus="
				+ actStatus + ", register=" + register + ", registDate=" + registDate + ", renewer=" + renewer
				+ ", renewDate=" + renewDate + "]";
	}

}

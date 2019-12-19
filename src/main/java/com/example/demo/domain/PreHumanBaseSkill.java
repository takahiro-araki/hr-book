package com.example.demo.domain;

/**
 * @author yuma.watanabe
 * 
 */
public class PreHumanBaseSkill {

	/** 仮登録ベーススキルデータID */
	private Integer preHumanBaseSkillId;
	/** 申請データID */
	private Integer orderId;
	/** ベーススキルID */
	private Integer baseSkillId;
	/** ベーススキルスコア */
	private Integer baseSkillScore;
	/** ベーススキル */
	private BaseSkill baseSkill;

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

	public Integer getBaseSkillScore() {
		return baseSkillScore;
	}

	public void setBaseSkillScore(Integer baseSkillScore) {
		this.baseSkillScore = baseSkillScore;
	}

	public BaseSkill getBaseSkill() {
		return baseSkill;
	}

	public void setBaseSkill(BaseSkill baseSkill) {
		this.baseSkill = baseSkill;
	}
	
	public String getRank() {
		if (baseSkillScore == 100) {
			return "s";
		} else if (baseSkillScore >= 80) {
			return "a";
		} else if (baseSkillScore >= 60) {
			return "b";
		} else if (baseSkillScore >= 40) {
			return "c";
		} else if (baseSkillScore >= 20) {
			return "d";
		} else if (baseSkillScore >= 0) {
			return "e";
		}
		return null;
	}

	@Override
	public String toString() {
		return "PreHumanBaseSkill [preHumanBaseSkillId=" + preHumanBaseSkillId + ", orderId=" + orderId
				+ ", baseSkillId=" + baseSkillId + ", baseSkillScore=" + baseSkillScore + ", baseSkill=" + baseSkill
				+ "]";
	}

}

package com.example.demo.domain;

/**
 * @author yuma.watanabe
 *
 */
public class PreHumanSubSkill {

	/** 仮登録サブスキルデータID */
	private Integer preHumanSubSkillId;
	/** 申請データID */
	private Integer orderId;
	/** サブスキルID */
	private Integer subSkillId;
	/** サブスキルスコア */
	private SubSkill subSkill;

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

	public SubSkill getSubSkill() {
		return subSkill;
	}

	public void setSubSkill(SubSkill subSkill) {
		this.subSkill = subSkill;
	}

	@Override
	public String toString() {
		return "PreHumanSubSkill [preHumanSubSkillId=" + preHumanSubSkillId + ", orderId=" + orderId + ", subSkillId="
				+ subSkillId + ", subSkill=" + subSkill + "]";
	}

}

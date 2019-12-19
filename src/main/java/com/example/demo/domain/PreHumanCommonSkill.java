package com.example.demo.domain;

/**
 * @author yuma.watanabe
 *
 */
public class PreHumanCommonSkill {

	/** 仮登録コモンスキルデータID */
	private Integer humanCommonSkillId;
	/** 申請データID */
	private Integer orderId;
	/** コモンスキルID */
	private Integer commonSkillId;
	/** コモンスキルスコア */
	private Integer commonSkillScore;
	/** コモンスキル */
	private CommonSkill commonSkill;

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

	public CommonSkill getCommonSkill() {
		return commonSkill;
	}

	public void setCommonSkill(CommonSkill commonSkill) {
		this.commonSkill = commonSkill;
	}

	@Override
	public String toString() {
		return "PreHumanCommonSkill [humanCommonSkillId=" + humanCommonSkillId + ", orderId=" + orderId
				+ ", commonSkillId=" + commonSkillId + ", commonSkillScore=" + commonSkillScore + ", commonSkill="
				+ commonSkill + "]";
	}

}

package com.example.demo.domain;

/**
 * 一般的なスキルのドメインクラス.
 * 
 * @author morishimashun
 *
 */
public class CommonSkill {

	/** コモンスキルId */
	private Integer commonSkillId;

	/** コモンスキル名 */
	private String commonSkillName;
	/** コモンスキルスコア */
	private Integer commonSkillScore;
	/** 説明文 */
	private String description;

	public Integer getCommonSkillId() {
		return commonSkillId;
	}

	public void setCommonSkillId(Integer commonSkillId) {
		this.commonSkillId = commonSkillId;
	}

	public String getCommonSkillName() {
		return commonSkillName;
	}

	public void setCommonSkillName(String commonSkillName) {
		this.commonSkillName = commonSkillName;
	}

	public Integer getCommonSkillScore() {
		return commonSkillScore;
	}

	public void setCommonSkillScore(Integer commonSkillScore) {
		this.commonSkillScore = commonSkillScore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CommonSkill [commonSkillId=" + commonSkillId + ", commonSkillName=" + commonSkillName
				+ ", commonSkillScore=" + commonSkillScore + ", description=" + description + "]";
	}

}

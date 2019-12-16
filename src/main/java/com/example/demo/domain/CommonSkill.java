package com.example.demo.domain;

/**
 * 一般的なスキルのドメインクラス.
 * 
 * @author morishimashun
 *
 */
public class CommonSkill {

	/** コモンスキル名 */
	private String commonSkillName;
	/** コモンスキルスコア */
	private Integer commonSkillScore;
	/** 説明文 */
	private String description;

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
		return "CommonSkill [commonSkillName=" + commonSkillName + ", commonSkillScore=" + commonSkillScore
				+ ", description=" + description + "]";
	}

}

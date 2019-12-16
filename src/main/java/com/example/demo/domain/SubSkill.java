package com.example.demo.domain;

/**
 * 補助的なスキルのドメインクラス.
 * 
 * @author morishimashun
 *
 */
public class SubSkill {

	/** サブスキル名 */
	private String subSkillName;
	/** サブスキルスコア */
	private Integer subSkillScore;
	/** 説明文 */
	private String description;

	public String getSubSkillName() {
		return subSkillName;
	}

	public void setSubSkillName(String subSkillName) {
		this.subSkillName = subSkillName;
	}

	public Integer getSubSkillScore() {
		return subSkillScore;
	}

	public void setSubSkillScore(Integer subSkillScore) {
		this.subSkillScore = subSkillScore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SubSkill [subSkillName=" + subSkillName + ", subSkillScore=" + subSkillScore + ", description="
				+ description + "]";
	}

}

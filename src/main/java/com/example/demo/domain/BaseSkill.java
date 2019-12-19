package com.example.demo.domain;

/**
 * 基本的なスキルのドメインクラス.
 * 
 * @author morishimashun
 *
 */
public class BaseSkill {

	/** ベーススキル名 */
	private String baseSkillName;
	/** ベーススキルスコア */
	private Integer baseSkillScore;

	public String getBaseSkillName() {
		return baseSkillName;
	}

	public void setBaseSkillName(String baseSkillName) {
		this.baseSkillName = baseSkillName;
	}

	public Integer getBaseSkillScore() {
		return baseSkillScore;
	}

	public void setBaseSkillScore(Integer baseSkillScore) {
		this.baseSkillScore = baseSkillScore;
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
		return "BaseSkill [baseSkillName=" + baseSkillName + ", baseSkillScore=" + baseSkillScore + "]";
	}

}

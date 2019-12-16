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

	@Override
	public String toString() {
		return "BaseSkill [baseSkillName=" + baseSkillName + ", baseSkillScore=" + baseSkillScore + "]";
	}

}

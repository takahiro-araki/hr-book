package com.example.demo.domain;

/**
 * 基本的なスキルのドメインクラス.
 * 
 * @author morishimashun
 *
 */
public class BaseSkill {

	/** ベーススキルId */
	private Integer baseSkillId;
	/** ベーススキル名 */
	private String baseSkillName;
	/** ベーススキルスコア */
	private Integer baseSkillScore;

	public Integer getBaseSkillId() {
		return baseSkillId;
	}

	public void setBaseSkillId(Integer baseSkillId) {
		this.baseSkillId = baseSkillId;
	}

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
		return "BaseSkill [baseSkillId=" + baseSkillId + ", baseSkillName=" + baseSkillName + ", baseSkillScore="
				+ baseSkillScore + "]";
	}

}

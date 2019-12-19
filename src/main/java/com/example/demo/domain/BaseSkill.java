package com.example.demo.domain;

/**
 * 基本的なスキルのドメインクラス.
 * 
 * @author morishimashun
 *
 */
public class BaseSkill {

	/** ベーススキルID */
	private Integer baseSkillId;
	/** ベーススキル名 */
	private String baseSkillName;

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

	@Override
	public String toString() {
		return "BaseSkill [baseSkillId=" + baseSkillId + ", baseSkillName=" + baseSkillName + "]";
	}

}

package com.example.demo.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author yuma.watanabe
 *
 */
public class InputSkillForm {
	@NotBlank(message = "社員番号を入力してください")
	@Pattern(message = "半角数字4桁で入力してください", regexp = "^[0-9]{4}$")
	private String empId;
	@NotBlank(message = "お名前を入れてください")
	private String humanName;
	@NotBlank(message = "入社日を入力してください")
	@Pattern(message = "入力形式に従ってください", regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$")
	private String joinDate;
	private MultipartFile iconImage;
	private List<String> baseSkillIds;
	private List<String> baseSkillScores;
	private List<String> commonSkillIds;
	private List<String> commonSkillScores;
	private List<String> subSkillIds;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getHumanName() {
		return humanName;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public MultipartFile getIconImage() {
		return iconImage;
	}

	public void setIconImage(MultipartFile iconImage) {
		this.iconImage = iconImage;
	}

	public List<String> getBaseSkillIds() {
		return baseSkillIds;
	}

	public void setBaseSkillIds(List<String> baseSkillIds) {
		this.baseSkillIds = baseSkillIds;
	}

	public List<String> getBaseSkillScores() {
		return baseSkillScores;
	}

	public void setBaseSkillScores(List<String> baseSkillScores) {
		this.baseSkillScores = baseSkillScores;
	}

	public List<String> getCommonSkillIds() {
		return commonSkillIds;
	}

	public void setCommonSkillIds(List<String> commonSkillIds) {
		this.commonSkillIds = commonSkillIds;
	}

	public List<String> getCommonSkillScores() {
		return commonSkillScores;
	}

	public void setCommonSkillScores(List<String> commonSkillScores) {
		this.commonSkillScores = commonSkillScores;
	}

	public List<String> getSubSkillIds() {
		return subSkillIds;
	}

	public void setSubSkillIds(List<String> subSkillIds) {
		this.subSkillIds = subSkillIds;
	}

	@Override
	public String toString() {
		return "InputSkillForm [empId=" + empId + ", humanName=" + humanName + ", joinDate=" + joinDate + ", iconImage="
				+ iconImage + ", baseSkillIds=" + baseSkillIds + ", baseSkillScores=" + baseSkillScores
				+ ", commonSkillIds=" + commonSkillIds + ", commonSkillScores=" + commonSkillScores + ", subSkillIds="
				+ subSkillIds + "]";
	}

}

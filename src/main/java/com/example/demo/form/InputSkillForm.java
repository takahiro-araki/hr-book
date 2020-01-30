package com.example.demo.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author yuma.watanabe
 *
 */
public class InputSkillForm {

	@NotBlank(message = "社員番号を入力してください")
//	@Pattern(message = "半角数字4桁で入力してください", regexp = "[0-9]{4}")
	private String empId;
	@NotBlank(message = "お名前を入れてください")
	private String humanName;
	@NotBlank(message = "会社名を入れてください")
	private String assignCompanyName;
	@NotBlank(message = "入社日を入力してください")
//	@Pattern(message = "入力形式に従って入力してください", regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$")
	private String joinDate;
	private MultipartFile iconImg;
	/**アイコン画像名（編集画面で使用） */
	private String iconImgName;
	private List<String> baseSkillIds;
	private List<String> baseSkillScores;
	private List<String> commonSkillIds;
	private List<String> commonSkillScores;
	private List<String> subSkillIds;
	
	
	public Integer getIntEmpId() {
		return Integer.parseInt(empId);
	}
	
	public Date getDateJoinData() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(joinDate);
		return date;
	}

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

	public String getAssignCompanyName() {
		return assignCompanyName;
	}

	public void setAssignCompanyName(String assignCompanyName) {
		this.assignCompanyName = assignCompanyName;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public MultipartFile getIconImg() {
		return iconImg;
	}

	public void setIconImg(MultipartFile iconImg) {
		this.iconImg = iconImg;
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

	public String getIconImgName() {
		return iconImgName;
	}

	public void setIconImgName(String iconImgName) {
		this.iconImgName = iconImgName;
	}

	@Override
	public String toString() {
		return "InputSkillForm [empId=" + empId + ", humanName=" + humanName + ", assignCompanyName="
				+ assignCompanyName + ", joinDate=" + joinDate + ", iconImg=" + iconImg + ", iconImgName=" + iconImgName
				+ ", baseSkillIds=" + baseSkillIds + ", baseSkillScores=" + baseSkillScores + ", commonSkillIds="
				+ commonSkillIds + ", commonSkillScores=" + commonSkillScores + ", subSkillIds=" + subSkillIds + "]";
	}
	
	


	

	
}

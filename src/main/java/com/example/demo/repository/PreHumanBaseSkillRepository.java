package com.example.demo.repository;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.PreHumanBaseSkill;

/**
 * @author yuma.watanabe
 *
 */
@Repository
public class PreHumanBaseSkillRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 申請中基本スキルを登録するメソッド.
	 * 
	 * @param preHumanBaseSkill
	 */
	public void insertPreHumanBaseSkill(PreHumanBaseSkill preHumanBaseSkill, Timestamp date, String humanName) {

		String insertSql = "INSERT INTO PRE_HUMAN_BASE_SKILLS(order_id,base_skill_id,base_skill_score,act_status,register,regist_date)"
				+ "VALUES(?,?,?,?,?,?)";

		jdbcTemplate.update(insertSql, preHumanBaseSkill.getOrderId(), preHumanBaseSkill.getBaseSkillId(),
				preHumanBaseSkill.getBaseSkillScore(), 1, humanName, date);

	}

}

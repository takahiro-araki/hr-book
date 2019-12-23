package com.example.demo.repository;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.PreHumanCommonSkill;

/**
 * @author yuma.watanabe
 *
 */
@Repository
public class PreHumanCommonSkillRepository {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 申請中の共通スキルを登録するメソッド.
	 * @param preHumanCommonSkill
	 */
	public void insertPreHumanCommonSkill(PreHumanCommonSkill preHumanCommonSkill,Timestamp date,String humanName) {
		String insertSql = "INSERT INTO PRE_HUMAN_COMMON_SKILLS(order_id,common_skill_id,common_skill_score,act_status,register,regist_date)"
				+ "VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(insertSql, preHumanCommonSkill.getOrderId(),preHumanCommonSkill.getCommonSkillId(),preHumanCommonSkill.getCommonSkillScore(),1,humanName,date);

	}
}

package com.example.demo.repository;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.PreHumanSubSkill;

/**
 * @author yuma.watanabe
 *
 */
@Repository
public class PreHumanSubSkillRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 申請中のサブスキルを登録するメソッド.
	 * @param preHumanSubSkill
	 */
	public void insertPreHumanSubSkill(PreHumanSubSkill preHumanSubSkill,Timestamp date,String humanName) {

		String insertSql = "INSERT INTO PRE_HUMAN_SUB_SKILLS(order_id,sub_skill_id,act_status,register,regist_date)"
				+ "VALUES(?,?,?,?,?)";
		jdbcTemplate.update(insertSql,preHumanSubSkill.getOrderId(),preHumanSubSkill.getSubSkillId(),1,humanName,date);

	}

}

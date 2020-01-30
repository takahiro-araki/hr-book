package com.example.demo.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.PreHumanBaseSkill;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreHumanBaseSkillRepositoryTest {
	
	private static final RowMapper<PreHumanBaseSkill> PRE_HUMAN_BASE_SKILL_ROW_MAPPER = (rs, i) -> {
		PreHumanBaseSkill preHumanBaseSkill = new PreHumanBaseSkill();
		preHumanBaseSkill.setOrderId(rs.getInt("order_id"));
		preHumanBaseSkill.setBaseSkillId(rs.getInt("base_skill_id"));
		preHumanBaseSkill.setBaseSkillScore(rs.getInt("base_skill_score"));
		return preHumanBaseSkill;
	};
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Test
	public void testInsertPreHumanBaseSkill() throws ParseException {
		Timestamp today = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2020/01/24").getTime());
		PreHumanBaseSkill preHumanBaseSkill = new PreHumanBaseSkill();
		preHumanBaseSkill.setOrderId(47);
		preHumanBaseSkill.setBaseSkillId(2);
		preHumanBaseSkill.setBaseSkillScore(50);
		
		String insertSql = "INSERT INTO PRE_HUMAN_BASE_SKILLS(order_id,base_skill_id,base_skill_score,act_status,register,regist_date)"
				+ "VALUES(?,?,?,?,?,?)";
		
		jdbcTemplate.update(insertSql, preHumanBaseSkill.getOrderId(), preHumanBaseSkill.getBaseSkillId(),
				preHumanBaseSkill.getBaseSkillScore(), 1, "管理者", today);
		
		Integer maxPreHumanBaseSkillId = template.queryForObject("select max(pre_human_base_skill_id) from pre_human_base_skills;", new MapSqlParameterSource(),
				Integer.class);
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("preHumanBaseSkillId", maxPreHumanBaseSkillId);
		PreHumanBaseSkill resultPreHumanBasaSkill = template.queryForObject("select * from pre_human_base_skills where pre_human_base_skill_id = :preHumanBaseSkillId;", param, PRE_HUMAN_BASE_SKILL_ROW_MAPPER);
	
		assertThat("オーダーIDが検索されていません", resultPreHumanBasaSkill.getOrderId(), is(47));
		assertThat("ベーススキルIDが検索されていません", resultPreHumanBasaSkill.getBaseSkillId(), is(2));
		assertThat("ベーススキルスコアが検索されていません", resultPreHumanBasaSkill.getBaseSkillScore(), is(50));
	}

}

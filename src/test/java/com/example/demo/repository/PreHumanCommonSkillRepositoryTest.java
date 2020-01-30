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

import com.example.demo.domain.PreHumanCommonSkill;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreHumanCommonSkillRepositoryTest {
	
	private static final RowMapper<PreHumanCommonSkill> PRE_HUMAN_COMMON_SKILL_ROW_MAPPER = (rs, i) -> {
		PreHumanCommonSkill preHumanCommonSkill = new PreHumanCommonSkill();
		preHumanCommonSkill.setOrderId(rs.getInt("order_id"));
		preHumanCommonSkill.setCommonSkillId(rs.getInt("common_skill_id"));
		preHumanCommonSkill.setCommonSkillScore(rs.getInt("common_skill_score"));
		return preHumanCommonSkill;
	};
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Test
	public void testInsertPreHumanCommonSkill() throws ParseException {
		Timestamp today = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2020/01/24").getTime());
		PreHumanCommonSkill preHumanCommonSkill = new PreHumanCommonSkill();
		preHumanCommonSkill.setOrderId(47);
		preHumanCommonSkill.setCommonSkillId(2);
		preHumanCommonSkill.setCommonSkillScore(50);
		
		String insertSql = "INSERT INTO PRE_HUMAN_COMMON_SKILLS(order_id,common_skill_id,common_skill_score,act_status,register,regist_date)"
				+ "VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(insertSql, preHumanCommonSkill.getOrderId(),preHumanCommonSkill.getCommonSkillId(),preHumanCommonSkill.getCommonSkillScore(),1,"管理者太郎",today);
		
		Integer maxPreHumanCommonSkillId = template.queryForObject("select max(pre_human_common_skill_id) from pre_human_common_skills;", new MapSqlParameterSource(),
				Integer.class);
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("preHumanCommonSkillId", maxPreHumanCommonSkillId);
		PreHumanCommonSkill resultPreHumanCommonSkill = template.queryForObject("select * from pre_human_Common_skills where pre_human_Common_skill_id = :preHumanCommonSkillId;", param, PRE_HUMAN_COMMON_SKILL_ROW_MAPPER);

		assertThat("オーダーIDが検索されていません", resultPreHumanCommonSkill.getOrderId(), is(47));
		assertThat("ベーススキルIDが検索されていません", resultPreHumanCommonSkill.getCommonSkillId(), is(2));
		assertThat("ベーススキルスコアが検索されていません", resultPreHumanCommonSkill.getCommonSkillScore(), is(50));
	}

}

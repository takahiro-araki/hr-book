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

import com.example.demo.domain.PreHumanSubSkill;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreHumanSubSkillRepositoryTest {
	
	private static final RowMapper<PreHumanSubSkill> PRE_HUMAN_SUB_SKILL_ROW_MAPPER = (rs, i) -> {
		PreHumanSubSkill preHumanSubSkill = new PreHumanSubSkill();
		preHumanSubSkill.setOrderId(rs.getInt("order_id"));
		preHumanSubSkill.setSubSkillId(rs.getInt("sub_skill_id"));
		return preHumanSubSkill;
	};
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Test
	public void testInsertPreHumanSubSkill() throws ParseException {
		Timestamp today = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2020/01/24").getTime());
		PreHumanSubSkill preHumanSubSkill = new PreHumanSubSkill();
		preHumanSubSkill.setOrderId(47);
		preHumanSubSkill.setSubSkillId(2);
		
		String insertSql = "INSERT INTO PRE_HUMAN_SUB_SKILLS(order_id,sub_skill_id,act_status,register,regist_date)"
				+ "VALUES(?,?,?,?,?)";
		jdbcTemplate.update(insertSql,preHumanSubSkill.getOrderId(),preHumanSubSkill.getSubSkillId(),1,"サブ太郎",today);
		
		Integer maxPreHumanSubSkillId = template.queryForObject("select max(pre_human_sub_skill_id) from pre_human_sub_skills;", new MapSqlParameterSource(),
				Integer.class);
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("preHumanSubSkillId", maxPreHumanSubSkillId);
		PreHumanSubSkill resultPreHumanSubSkill = template.queryForObject("select * from pre_human_sub_skills where pre_human_sub_skill_id = :preHumanSubSkillId;", param, PRE_HUMAN_SUB_SKILL_ROW_MAPPER);
		
		assertThat("オーダーIDが検索されていません", resultPreHumanSubSkill.getOrderId(), is(47));
		assertThat("ベーススキルIDが検索されていません", resultPreHumanSubSkill.getSubSkillId(), is(2));
	}

}

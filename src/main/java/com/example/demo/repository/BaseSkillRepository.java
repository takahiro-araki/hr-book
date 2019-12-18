package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.BaseSkill;

/**
 * @author yuma.watanabe
 *
 */
@Repository
public class BaseSkillRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<BaseSkill> BASE_SKILL_ROW_MAPPER = (rs, i) -> {
		BaseSkill baseSkill = new BaseSkill();
		baseSkill.setBaseSkillId(rs.getInt("base_skill_id"));
		baseSkill.setBaseSkillName(rs.getString("base_skill_name"));
		return baseSkill;
	};

	public List<BaseSkill> findAll() {
		String findAllSql = "SELECT base_skill_id,base_skill_name from base_skills";
		List<BaseSkill> baseSkillList = template.query(findAllSql, BASE_SKILL_ROW_MAPPER);
		return baseSkillList;
	}

}

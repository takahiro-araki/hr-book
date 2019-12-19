package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.SubSkill;

/**
 * @author yuma.watanabe
 *
 */
@Repository
public class SubSkillRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<SubSkill> SUB_SKILL_ROW_MAPPER = (rs, i) -> {
		SubSkill subSkill = new SubSkill();
		subSkill.setSubSkillId(rs.getInt("sub_skill_id"));
		subSkill.setSubSkillName(rs.getString("sub_skill_name"));
		return subSkill;
	};

	public List<SubSkill> findAll() {
		String findAllSql = "SELECT sub_skill_id,sub_skill_name FROM sub_skills";
		List<SubSkill> subSkillList = template.query(findAllSql, SUB_SKILL_ROW_MAPPER);
		return subSkillList;
	}

}

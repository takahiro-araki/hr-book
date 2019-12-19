package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.CommonSkill;

/**
 * @author yuma.watanabe
 *
 */
@Repository
public class CommonSkillRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<CommonSkill> COMMON_SKILL_ROW_MAPPER = (rs, i) -> {
		CommonSkill commonSkill = new CommonSkill();
		commonSkill.setCommonSkillId(rs.getInt("common_skill_id"));
		commonSkill.setCommonSkillName(rs.getString("common_skill_name"));
		return commonSkill;
	};
	
	public List<CommonSkill> findAll(){
		String findAllSql = "SELECT common_skill_id,common_skill_name FROM common_skills";
		List<CommonSkill> commonSkillList = template.query(findAllSql,COMMON_SKILL_ROW_MAPPER);
		return commonSkillList;
	}

}

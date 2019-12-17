package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.BaseSkill;
import com.example.demo.domain.CommonSkill;
import com.example.demo.domain.Human;
import com.example.demo.domain.SubSkill;

/**
 * 登録したエンジニアのDBを操作するリポジトリ.
 * 
 * @author morishimashun
 *
 */
@Repository
public class HumanRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	public static final ResultSetExtractor<List<Human>> EXTRACTOR = (rs) -> {
		List<Human> humanList = new ArrayList<>();
		List<BaseSkill> baseSkills = null;
		List<CommonSkill> commonSkills = null;
		List<SubSkill> subSkills = null;
		int beforeHumanId = -1;
		String base = "base";
		String common = "common";
		String sub = "sub";

		while (rs.next()) {
			int nowHumanId = rs.getInt("human_id");
			if (beforeHumanId != nowHumanId) {
				Human human = new Human();
				human.setEmpId(rs.getInt("emp_id"));
				human.setHumanName(rs.getString("human_name"));
				human.setJoinDate(rs.getDate("join_date"));
				human.setAssignCompanyName(rs.getString("assign_company_name"));
				human.setIcon_img(rs.getString("icon_img"));
				human.setOrderStatus(rs.getInt("order_status"));
				baseSkills = new ArrayList<>();
				human.setBaseSkills(baseSkills);
				commonSkills = new ArrayList<>();
				human.setCommonSkills(commonSkills);
				subSkills = new ArrayList<>();
				human.setSubSkills(subSkills);

				humanList.add(human);
				beforeHumanId = nowHumanId;
			}
			if (base.equals(rs.getString("skill_type"))) {
				BaseSkill baseSkill = new BaseSkill();
				baseSkill.setBaseSkillName(rs.getString("skill_name"));
				baseSkill.setBaseSkillScore(rs.getInt("score"));
				baseSkills.add(baseSkill);
			} else if (common.equals(rs.getString("skill_type"))) {
				CommonSkill commonSkill = new CommonSkill();
				commonSkill.setCommonSkillName(rs.getString("skill_name"));
				commonSkill.setCommonSkillScore(rs.getInt("score"));
				commonSkill.setDescription(rs.getString("description"));
				commonSkills.add(commonSkill);
			} else if (sub.equals(rs.getString("skill_type"))) {
				SubSkill subSkill = new SubSkill();
				subSkill.setSubSkillName(rs.getString("skill_name"));
				subSkill.setSubSkillScore(rs.getInt("score"));
				subSkill.setDescription(rs.getString("description"));
				subSkills.add(subSkill);
			}
		}
		return humanList;
	};

	/**
	 * エンジニア一人をloadするメソッド.
	 * 
	 * @param humanId
	 * @return エンジニア情報
	 */
	public Human load(Integer humanId) {
		String sql = "SELECT " + 
				"  j2.human_id, " + 
				"  j2.emp_id, " + 
				"  j2.human_name, " + 
				"  j2.join_date, " + 
				"  j2.icon_img, " + 
				"  j2.assign_company_name, " + 
				"  j2.skill_id, " + 
				"  j2.skill_name, " + 
				"  j2.score, " + 
				"  j2.skill_type, " + 
				"  j2.description, " + 
				"  j2.order_status, " + 
				"  j2.order_id, " + 
				"  j2.order_ver " + 
				"FROM ( " + 
				"    SELECT " + 
				"      human_id, " + 
				"      emp_id, " + 
				"      human_name, " + 
				"      join_date, " + 
				"      icon_img, " + 
				"      assign_company_name, " + 
				"      skill_id, " + 
				"      skill_name, " + 
				"      score, " + 
				"      skill_type, " + 
				"      description, " + 
				"      order_status, " + 
				"      order_id, " + 
				"      rank() over( " + 
				"        partition by human_id " + 
				"        ORDER BY " + 
				"          order_id DESC " + 
				"      ) AS order_ver " + 
				"    FROM ( " + 
				"        ( " + 
				"          SELECT " + 
				"            human_id, " + 
				"            emp_id, " + 
				"            human_name, " + 
				"            join_date, " + 
				"            icon_img, " + 
				"            assign_company_name, " + 
				"            base_skill_id AS skill_id, " + 
				"            base_skill_name AS skill_name, " + 
				"            base_skill_score AS score, " + 
				"            'base' AS skill_type, " + 
				"            '' AS description, " + 
				"            order_status, " + 
				"            order_id " + 
				"          FROM humans " + 
				"          LEFT JOIN orders USING(human_id) " + 
				"          LEFT JOIN pre_human_base_skills USING(order_id) " + 
				"          LEFT JOIN base_skills USING(base_skill_id) " + 
				"        ) " + 
				"        UNION " + 
				"          ( " + 
				"            SELECT " + 
				"              human_id, " + 
				"              emp_id, " + 
				"              human_name, " + 
				"              join_date, " + 
				"              icon_img, " + 
				"              assign_company_name, " + 
				"              common_skill_id AS skill_id, " + 
				"              common_skill_name AS skill_name, " + 
				"              common_skill_score AS score, " + 
				"              'common' AS skill_type, " + 
				"              description, " + 
				"              order_status, " + 
				"              order_id " + 
				"            FROM humans " + 
				"            LEFT JOIN orders USING(human_id) " + 
				"            LEFT JOIN pre_human_common_skills USING(order_id) " + 
				"            LEFT JOIN common_skills USING(common_skill_id) " + 
				"          ) " + 
				"        UNION " + 
				"          ( " + 
				"            SELECT " + 
				"              human_id, " + 
				"              emp_id, " + 
				"              human_name, " + 
				"              join_date, " + 
				"              icon_img, " + 
				"              assign_company_name, " + 
				"              sub_skill_id AS skill_id, " + 
				"              sub_skill_name AS skill_name, " + 
				"              sub_skill_status_type AS score, " + 
				"              'sub' AS skill_type, " + 
				"              description, " + 
				"              order_status, " + 
				"              order_id " + 
				"            FROM humans " + 
				"            LEFT JOIN orders USING(human_id) " + 
				"            LEFT JOIN pre_human_sub_skills USING(order_id) " + 
				"            LEFT JOIN sub_skills USING(sub_skill_id) " + 
				"          ) " + 
				"      ) AS j " + 
				"  ) AS j2 " + 
				" WHERE " + 
				"  order_ver = 1 " + 
				"  AND human_id =:humanId " + 
				" ORDER BY " + 
				"  human_id, " + 
				"  skill_type, " + 
				"  skill_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("humanId", humanId);
		List<Human> humanList = template.query(sql, param, EXTRACTOR);
		if (humanList.size() == 0) {
			return null;
		}
		return humanList.get(0);
	}

}

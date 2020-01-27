package com.example.demo.repository;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.common.ReadSql;
import com.example.demo.common.UserRole;
import com.example.demo.domain.BaseSkill;
import com.example.demo.domain.CommonSkill;
import com.example.demo.domain.Human;
import com.example.demo.domain.PreHumanBaseSkill;
import com.example.demo.domain.PreHumanCommonSkill;
import com.example.demo.domain.PreHumanSubSkill;
import com.example.demo.domain.SubSkill;
import com.example.demo.form.ShowHumanListForm;

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

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ReadSql readSql;

	static final String SQL_LOAD = "src/main/resources/sql/load.sql";

	static final String SQL_LOAD_AUTH = "src/main/resources/sql/load_authenticated.sql";
	
	static final String SQL_SHOW_LIST = "src/main/resources/sql/showList.sql";

	public static final ResultSetExtractor<List<Human>> EXTRACTOR = (rs) -> {
		List<Human> humanList = new ArrayList<>();
		List<PreHumanBaseSkill> baseSkills = null;
		List<PreHumanCommonSkill> commonSkills = null;
		List<PreHumanSubSkill> subSkills = null;
		int beforeHumanId = -1;
		String base = "base";
		String common = "common";
		String sub = "sub";

		while (rs.next()) {
			int nowHumanId = rs.getInt("human_id");
			if (beforeHumanId != nowHumanId) {
				Human human = new Human();
				human.setHumanId(rs.getInt("human_id"));
				human.setEmpId(rs.getInt("emp_id"));
				human.setHumanName(rs.getString("human_name"));
				human.setJoinDate(rs.getDate("join_date"));
				human.setAssignCompanyName(rs.getString("assign_company_name"));
				human.setIconImg(rs.getString("icon_img"));
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
				PreHumanBaseSkill preHumanBaseSkill = new PreHumanBaseSkill();
				preHumanBaseSkill.setBaseSkillScore(rs.getInt("score"));
				preHumanBaseSkill.setBaseSkillId(rs.getInt("skill_id"));
				BaseSkill baseSkill = new BaseSkill();
				baseSkill.setBaseSkillName(rs.getString("skill_name"));
				baseSkill.setBaseSkillId(rs.getInt("skill_id"));
				preHumanBaseSkill.setBaseSkill(baseSkill);
				baseSkills.add(preHumanBaseSkill);
			} else if (common.equals(rs.getString("skill_type"))) {
				PreHumanCommonSkill preHumanCommonSkill = new PreHumanCommonSkill();
				CommonSkill commonSkill = new CommonSkill();
				commonSkill.setCommonSkillName(rs.getString("skill_name"));
				preHumanCommonSkill.setCommonSkillScore(rs.getInt("score"));
				preHumanCommonSkill.setCommonSkillId(rs.getInt("skill_id"));
				commonSkill.setDescription(rs.getString("description"));
				commonSkill.setCommonSkillId(rs.getInt("skill_id"));
				preHumanCommonSkill.setCommonSkill(commonSkill);
				commonSkills.add(preHumanCommonSkill);
			} else if (sub.equals(rs.getString("skill_type"))) {
				PreHumanSubSkill preHumanSubSkill = new PreHumanSubSkill();
				SubSkill subSkill = new SubSkill();
				preHumanSubSkill.setSubSkillId(rs.getInt("skill_id"));
				subSkill.setSubSkillName(rs.getString("skill_name"));
				subSkill.setSubSkillStatusType(rs.getInt("score"));
				subSkill.setDescription(rs.getString("description"));
				subSkill.setSubSkillId(rs.getInt("skill_id"));
				preHumanSubSkill.setSubSkill(subSkill);
				subSkills.add(preHumanSubSkill);
			}
		}
		return humanList;
	};

	public static final ResultSetExtractor<List<Human>> EXTRACTOR_ONLY_BASESKILL = (rs) -> {
		List<Human> humanList = new ArrayList<>();
		List<PreHumanBaseSkill> baseSkills = null;
		int beforeHumanId = -1;
		String base = "base";
		while (rs.next()) {
			int nowHumanId = rs.getInt("human_id");
			if (beforeHumanId != nowHumanId) {
				Human human = new Human();
				human.setHumanId(rs.getInt("human_id"));
				human.setEmpId(rs.getInt("emp_id"));
				human.setHumanName(rs.getString("human_name"));
				human.setUserId(rs.getInt("user_id"));
				human.setJoinDate(rs.getDate("join_date"));
				human.setAssignCompanyName(rs.getString("assign_company_name"));
				human.setIconImg(rs.getString("icon_img"));
				human.setOrderStatus(rs.getInt("order_status"));
				baseSkills = new ArrayList<>();
				human.setBaseSkills(baseSkills);
				humanList.add(human);
				beforeHumanId = nowHumanId;
			}
			if (base.equals(rs.getString("skill_type"))) {
				PreHumanBaseSkill preHumanBaseSkill = new PreHumanBaseSkill();
				preHumanBaseSkill.setBaseSkillScore(rs.getInt("score"));
				preHumanBaseSkill.setBaseSkillId(rs.getInt("skill_id"));
				BaseSkill baseSkill = new BaseSkill();
				baseSkill.setBaseSkillName(rs.getString("skill_name"));
				baseSkill.setBaseSkillId(rs.getInt("skill_id"));
				preHumanBaseSkill.setBaseSkill(baseSkill);
				baseSkills.add(preHumanBaseSkill);
			}
		}
		return humanList;
	};

	/**
	 * エンジニア一人をloadするメソッド.
	 * 
	 * @param empId
	 * @return エンジニア情報
	 * @throws IOException
	 */
	public Human load(Integer empId) throws IOException {
		String sql = readSql.readAll(SQL_LOAD);
		SqlParameterSource param = new MapSqlParameterSource().addValue("empId", empId);
		List<Human> humanList = template.query(sql, param, EXTRACTOR);
		if (humanList.size() == 0) {
			return null;
		}
		return humanList.get(0);
	}
	
	/**
	 * 認証済みのエンジニア一人をloadするメソッド.
	 * 
	 * @param empId
	 * @return 認証済みのエンジニア情報
	 * @throws IOException
	 */
	public Human loadAuth(Integer empId) throws IOException {
		String sql = readSql.readAll(SQL_LOAD_AUTH);
		SqlParameterSource param = new MapSqlParameterSource().addValue("empId", empId);
		List<Human> humanList = template.query(sql, param, EXTRACTOR);
		if (humanList.size() == 0) {
			return null;
		}
		return humanList.get(0);
	}

	/**
	 * 
	 * エンジニア１人を登録するメソッド.
	 * 
	 * @param human
	 */
	public Integer insertHuman(Human human, Timestamp date) {
		String insertSql = "INSERT INTO HUMANS(user_id,emp_id,human_name,join_date,icon_img,act_status,version_num,register,regist_date)"
				+ "VALUES(?,?,?,?,?,?,?,?,?) RETURNING human_id";
		Integer humanId = jdbcTemplate.queryForObject(insertSql, Integer.class, human.getUserId(), human.getEmpId(),
				human.getHumanName(), human.getJoinDate(), human.getIconImg(), 1, 1, human.getHumanName(), date);
		return humanId;
	}

	/*
	 * エンジニアを一覧表示するメソッド.
	 * 
	 * userRoleが0～１の場合、全エンジニアを表示、userRoleが2の場合、ユーザーと全員がアクセス可能なエンジニアを表示
	 * 
	 * @param ユーザーロール
	 * 
	 * @return エンジニア情報リスト
	 */
	public List<Human> getList(ShowHumanListForm form, Integer userRole, Integer userId) throws IOException {
		String sql = readSql.readAll(SQL_SHOW_LIST);
		StringBuilder sb = new StringBuilder(sql);
		List<Human> humanList = null;
		UserRole role = new UserRole();

		if (userRole == role.ADMINISTRATOR || userRole == role.INSIDE_OFFICE) {
			if (form.search()) {
				if (form.getName() != "") {
					sb.append("WHERE (order_ver = 1 AND act_status = 1) AND ");
					sb.append(" j2.human_name LIKE :name ");
				}
				if (form.getOrder() != "") {
					sb.append("WHERE order_ver = 1 ");
					if (form.getOrder().equals("joinDate")) {
						sb.append("ORDER BY  " + "	j2.join_date " + "desc, " + "j2.human_id, "+"skill_type, "+"skill_id ");
					} else {
						sb.append("ORDER BY  " + "j2.order_status, " + "j2.human_id, "+"skill_type, "+"skill_id ");
					}
				}
				SqlParameterSource param = new MapSqlParameterSource().addValue("name", ("%" + form.getName() + "%"));
				humanList = template.query(sb.toString(), param, EXTRACTOR_ONLY_BASESKILL);
			} else {
				humanList = template.query(sb.toString(), EXTRACTOR_ONLY_BASESKILL);
			}
		} else {
			sb.append("WHERE (order_ver = 1 AND act_status = 1) AND ");
			if (form.getName() != "") {
				sb.append("( j2.user_id=:userId ) OR ");
				sb.append(" ( (j2.emp_id= 999 OR j2.emp_id=99) ");
				sb.append(" AND j2.human_name LIKE :name ) ");
				sb.append("ORDER BY  "  + "j2.human_id, "+"skill_type, "+"skill_id ");
				SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("name",
						("%" + form.getName() + "%"));
				humanList = template.query(sb.toString(), param, EXTRACTOR_ONLY_BASESKILL);
			} else {
				sb.append(" ( j2.user_id=:userId  OR ");
				sb.append(" (j2.emp_id= 999 OR j2.emp_id=99) ) ");
				sb.append("ORDER BY  "  + "j2.human_id, "+"skill_type, "+"skill_id ");
				SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
				humanList = template.query(sb.toString(), param, EXTRACTOR_ONLY_BASESKILL);
			}
		}
		return humanList;
	}

}

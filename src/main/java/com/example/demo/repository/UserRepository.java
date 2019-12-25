package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

/**
 * ログインするユーザーのDBを操作するリポジトリ.
 * 
 * @author atsushi
 *
 */
@Repository
public class UserRepository {

	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setUserId(rs.getInt("userId"));
		user.setEmpId(rs.getInt("empId"));
		user.setName(rs.getString("name"));
		user.setMailAddress(rs.getString("mailAddress"));
		user.setPassword(rs.getString("password"));
		user.setUserRole(rs.getInt("userRole"));
		user.setActStatus(rs.getInt("actStatus"));
		return user;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * メールアドレスでユーザー情報を検索する.
	 * 
	 * @param mailAddress
	 * @return
	 */
	public User findByMailAddress(String mailAddress) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);
		String sql = "SELECT (user_id,emp_id,user_name,mail_address,password,user_roll,act_status)  " + "FROM Users  "
				+ "WHERE mail_address = :mailAddress  " + ";";
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		} else {
			return userList.get(0);
		}
	}

	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO USERS ( " + "    emp_id, " + "    user_name, " + "    mail_address, "
				+ "    password, " + "    user_roll, " + "    act_status " + "  ) " + "VALUES "
				+ "  (:empId, :name, :mailAddress, :password, :userRole, 1)";
		template.update(sql, param);
	}
}

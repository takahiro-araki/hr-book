package com.example.demo.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setEmpId(rs.getInt("emp_id"));
		user.setUserName(rs.getString("user_name"));
		user.setMailAddress(rs.getString("mail_address"));
		user.setPassword(rs.getString("password"));
		user.setUserRole(rs.getInt("user_roll"));
		user.setActStatus(rs.getInt("act_status"));
		return user;
	};
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Test
	public void testFindByMailAddress() {
		User resultUser = userRepository.findByMailAddress("2222@2222");
		assertThat("社員番号が検索されていません", resultUser.getEmpId(), is(2222));
		assertThat("名前が検索されていません", resultUser.getUserName(), is("相澤"));
		assertThat("パスワードが検索されていません", resultUser.getPassword(), is("$2a$10$tSMkiOU8ePRuVdd0pbVlU.1bbVpaLQo4eaQBSAJNCuKVBEmIWYPbW"));
		assertThat("ユーザーロールが検索されていません", resultUser.getUserRole(), is(2));
		assertThat("アクトステータスが検索されていません", resultUser.getActStatus(), is(1));
	}
	
	@Test
	public void testInsertUser() {
		User user = new User();
		user.setEmpId(1111);
		user.setUserName("1111太郎");
		user.setMailAddress("1111@1111");
		user.setPassword("1111");
		user.setUserRole(2);
		userRepository.insert(user);
		System.out.println("インサートが完了しました。");
		
		Integer maxUserId = template.queryForObject("select max(user_id) from users;", new MapSqlParameterSource(),
				Integer.class);
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("userId", maxUserId);
		User resultUser = template.queryForObject("select * from users where user_id = :userId;",
				param, USER_ROW_MAPPER);
		
		assertThat("社員番号が検索されていません", resultUser.getEmpId(), is(1111));
		assertThat("名前が検索されていません", resultUser.getUserName(), is("1111太郎"));
		assertThat("メールアドレスが検索されていません", resultUser.getMailAddress(), is("1111@1111"));
		assertThat("パスワードが検索されていません", resultUser.getPassword(), is("1111"));
		assertThat("ユーザーロールが検索されていません", resultUser.getUserRole(), is(2));
		assertThat("アクトステータスが検索されていません", resultUser.getActStatus(), is(1));
	}
}

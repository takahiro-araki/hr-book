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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Order;
import com.example.demo.repository.OrderRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {
	
	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		Order order = new Order();
		order.setHumanId(rs.getInt("human_id"));
		order.setOrderStatus(rs.getInt("order_status"));
		order.setActStatus(rs.getInt("act_status"));
		order.setVersionNum(rs.getInt("version_num"));
		order.setRegister(rs.getString("register"));
		order.setRegistDate(rs.getTimestamp("regist_date"));
		return order;
	};
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Test
	public void testInsert() throws ParseException {
		Timestamp today = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2020/01/24").getTime());
		Order order = new Order();
		order.setHumanId(50);
		order.setOrderStatus(1);
		order.setActStatus(1);
		order.setVersionNum(1);
		orderRepository.insertOrder(order, "100太郎", today);
		System.out.println("インサートが完了しました。");
		
		Integer maxOrderId = template.queryForObject("select max(order_id) from orders;", new MapSqlParameterSource(),
				Integer.class);
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("orderId", maxOrderId);
		Order resultOrder = template.queryForObject("select * from orders where order_id = :orderId;", param, ORDER_ROW_MAPPER);
	
		assertThat("社員番号が検索されていません", resultOrder.getHumanId(), is(50));
		assertThat("オーダーステータスが検索されていません", resultOrder.getOrderStatus(), is(1));
		assertThat("アクトステータスが検索されていません", resultOrder.getActStatus(), is(1));
		assertThat("バージョンナンバーが検索されていません", resultOrder.getVersionNum(), is(1));
		assertThat("登録者が検索されていません", resultOrder.getRegister(), is("100太郎"));
		assertThat("登録日が検索されていません", resultOrder.getRegistDate(), is(today));
	}
}

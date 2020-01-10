package com.example.demo.repository;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Order;

/**
 * @author yuma.watanabe
 *
 */
@Repository
public class OrderRepository {


	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 承認情報をするメソッド.
	 * 
	 * @param order
	 */
	public Integer insertOrder(Order order, String humanName, Timestamp date) {
		String insertSql = "INSERT INTO ORDERS(human_id,order_status,act_status,version_num,register,regist_date)"
				+ "VALUES(?,?,?,?,?,?) RETURNING order_id";
		Integer orderId = jdbcTemplate.queryForObject(insertSql, Integer.class,order.getHumanId(),order.getOrderStatus(),order.getActStatus(),order.getVersionNum(),humanName, date);
		return orderId;

	}
	

}

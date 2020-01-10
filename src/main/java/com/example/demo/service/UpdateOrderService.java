package com.example.demo.service;

import java.sql.Timestamp;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Human;
import com.example.demo.domain.LoginUser;
import com.example.demo.domain.Order;
import com.example.demo.domain.PreHumanBaseSkill;
import com.example.demo.domain.PreHumanCommonSkill;
import com.example.demo.domain.PreHumanSubSkill;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PreHumanBaseSkillRepository;
import com.example.demo.repository.PreHumanCommonSkillRepository;
import com.example.demo.repository.PreHumanSubSkillRepository;

/**
 * 登録されたオーダーを変更するサービスクラス.
 * 
 * @author shun.m
 *
 */
@Service
@Transactional
public class UpdateOrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PreHumanBaseSkillRepository preHumanBaseSkillRepository;
	@Autowired
	private PreHumanCommonSkillRepository preHumanCommonSkillRepository;
	@Autowired
	private PreHumanSubSkillRepository preHumanSubSkillRepository;
	@Autowired
	private InputSkillService inputSkillService;

	/**
	 * オーダーを承認する.
	 * 
	 * @param loginUser 操作しているユーザー
	 * @param human     エンジニア
	 * @throws ParseException
	 */
	public void authenOrder(@AuthenticationPrincipal LoginUser loginUser, Human human) throws ParseException {
		String registerName = loginUser.getUser().getUserName();
		Timestamp registDate = inputSkillService.returnToday();
		Order order = new Order();
		order.setHumanId(human.getHumanId());
		order.setActStatus(1);
		order.setOrderStatus(2);
		order.setVersionNum(1);
		order.setRegister(loginUser.getUsername());
		order.setRegistDate(registDate);
		Integer orderId = orderRepository.insertOrder(order, registerName, registDate);
		for (PreHumanBaseSkill preHumanBaseSkill : human.getBaseSkills()) {
			preHumanBaseSkill.setOrderId(orderId);
			preHumanBaseSkillRepository.insertPreHumanBaseSkill(preHumanBaseSkill, registDate, registerName);
		}
		for (PreHumanCommonSkill preHumanCommonSkill : human.getCommonSkills()) {
			preHumanCommonSkill.setOrderId(orderId);
			preHumanCommonSkillRepository.insertPreHumanCommonSkill(preHumanCommonSkill, registDate, registerName);
		}
		for (PreHumanSubSkill preHumanSubSkill : human.getSubSkills()) {
			preHumanSubSkill.setOrderId(orderId);
			preHumanSubSkillRepository.insertPreHumanSubSkill(preHumanSubSkill, registDate, registerName);
		}
	}

}

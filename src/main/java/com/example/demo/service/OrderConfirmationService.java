package com.example.demo.service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BaseSkill;
import com.example.demo.domain.CommonSkill;
import com.example.demo.domain.Human;
import com.example.demo.domain.LoginUser;
import com.example.demo.domain.Order;
import com.example.demo.domain.PreHumanBaseSkill;
import com.example.demo.domain.PreHumanCommonSkill;
import com.example.demo.domain.PreHumanSubSkill;
import com.example.demo.domain.SubSkill;
import com.example.demo.form.InputSkillForm;
import com.example.demo.repository.BaseSkillRepository;
import com.example.demo.repository.CommonSkillRepository;
import com.example.demo.repository.HumanRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PreHumanBaseSkillRepository;
import com.example.demo.repository.PreHumanCommonSkillRepository;
import com.example.demo.repository.PreHumanSubSkillRepository;
import com.example.demo.repository.SubSkillRepository;

@Service
public class OrderConfirmationService {

	@Autowired
	private HumanRepository humanRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PreHumanBaseSkillRepository preHumanBaseSkillRepository;

	@Autowired
	private PreHumanCommonSkillRepository preHumanCommonSkillRepository;

	@Autowired
	private PreHumanSubSkillRepository preHumanSubSkillRepository;

	@Autowired
	private BaseSkillRepository baseSkillRepository;

	@Autowired
	private CommonSkillRepository commonSkillRepository;

	@Autowired
	private SubSkillRepository subSkillRepository;

	public List<BaseSkill> findAllBaseSkill() {
		return baseSkillRepository.findAll();
	}

	public List<CommonSkill> findAllCommonSkill() {
		return commonSkillRepository.findAll();
	}

	public List<SubSkill> findAllSubSkill() {
		return subSkillRepository.findAll();
	}

	/**
	 * human情報をドメインに格納するメソッド.
	 * 
	 * @param form
	 * @throws ParseException
	 */
	public void insertHuman(@AuthenticationPrincipal LoginUser loginUser,InputSkillForm form, byte[] iconImageByte, String iconImageName) throws ParseException {
		Human human = new Human();
		human.setUserId(loginUser.getUser().getUserId());
		human.setEmpId(Integer.parseInt(form.getEmpId()));
		human.setHumanName(form.getHumanName());
		human.setJoinDate(Date.valueOf(form.getJoinDate()));
		human.setIconImg(iconImageName);
		// ここに写真を保存するメソッドを呼び出す
		createFile(iconImageByte, iconImageName);
		Integer humanId = humanRepository.insertHuman(human, returnToday());
		insertOrders(form, humanId);
	}

	/**
	 * 基礎スキルをドメインに格納するメソッド.
	 * 
	 * @param form
	 * @param orderId
	 * @throws ParseException
	 */
	public void insertPreHumanBaseSkill(InputSkillForm form, Integer orderId) throws ParseException {
		PreHumanBaseSkill preHumanBaseSkill = new PreHumanBaseSkill();
		preHumanBaseSkill.setOrderId(orderId);
		for (int i = 0; i < form.getBaseSkillIds().size(); i++) {
			preHumanBaseSkill.setBaseSkillId(Integer.parseInt(form.getBaseSkillIds().get(i)));
			preHumanBaseSkill.setBaseSkillScore(Integer.parseInt(form.getBaseSkillScores().get(i)));
			preHumanBaseSkillRepository.insertPreHumanBaseSkill(preHumanBaseSkill, returnToday(), form.getHumanName());
		}
		insertPreHumanCommonSkill(form, orderId);
	}

	/**
	 * 共通スキルをドメインに格納するメソッド.
	 * 
	 * @param form
	 * @param orderId
	 * @throws ParseException
	 */
	public void insertPreHumanCommonSkill(InputSkillForm form, Integer orderId) throws ParseException {
		PreHumanCommonSkill preHumanCommonSkill = new PreHumanCommonSkill();
		preHumanCommonSkill.setOrderId(orderId);
		for (int i = 0; i < form.getCommonSkillIds().size(); i++) {
			preHumanCommonSkill.setCommonSkillId(Integer.parseInt(form.getCommonSkillIds().get(i)));
			preHumanCommonSkill.setCommonSkillScore(Integer.parseInt(form.getCommonSkillScores().get(i)));
			preHumanCommonSkillRepository.insertPreHumanCommonSkill(preHumanCommonSkill, returnToday(),
					form.getHumanName());
		}
		insertPreHumanSubSkill(form, orderId);
	}

	/**
	 * サブスキルをドメインに格納するメソッド.
	 * 
	 * @param form
	 * @param orderId
	 */
	public void insertPreHumanSubSkill(InputSkillForm form, Integer orderId) {
		PreHumanSubSkill preHumanSubSkill = new PreHumanSubSkill();
		preHumanSubSkill.setOrderId(orderId);
		for (int i = 0; i < form.getSubSkillIds().size(); i++) {
			preHumanSubSkill.setSubSkillId(Integer.parseInt(form.getSubSkillIds().get(i)));
			try {
				preHumanSubSkillRepository.insertPreHumanSubSkill(preHumanSubSkill, returnToday(), form.getHumanName());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 承認管理情報をドメインに格納するメソッド.
	 * 
	 * @param form
	 * @param humanId
	 * @throws ParseException
	 */
	public void insertOrders(InputSkillForm form, Integer humanId) throws ParseException {
		Order order = new Order();
		order.setHumanId(humanId);
		order.setActStatus(1);
		order.setOrderStatus(1);
		order.setActStatus(1);
		order.setVersionNum(1);
		order.setRegister(form.getHumanName());
		order.setRegistDate(returnToday());
		Integer orderId = orderRepository.insertOrder(order, form.getHumanName(), returnToday());
		insertPreHumanBaseSkill(form, orderId);
	}

	/**
	 * 入力した日付を返すメソッド.
	 * 
	 * @return 入力した日付
	 * @throws ParseException
	 */
	public Timestamp returnToday() throws ParseException {
		return new Timestamp(System.currentTimeMillis());

	}

	/**
	 * エンジニアの画像を書き込むファイルを作成するメソッド.
	 * 
	 * @param file
	 */
	public void createFile(byte[] iconImageByte, String iconImageName) {
		Path path = Paths.get("../hr_book/src/main/resources/static/img/human_img/" + iconImageName);
		try {
			Files.createFile(path);
			writeImage(path, iconImageByte);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 画像をファイルに書き込むメソッド.
	 * 
	 * @param path
	 * @param imageFile
	 * @throws IOException
	 */
	public void writeImage(Path path, byte[] iconImageByte)  {
		try (OutputStream os = Files.newOutputStream(path, StandardOpenOption.CREATE)) {
			byte[] bytes = iconImageByte;
			os.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

}

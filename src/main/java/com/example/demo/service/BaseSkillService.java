package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.BaseSkill;
import com.example.demo.repository.BaseSkillRepository;

/**
 * @author yuma.watanabe
 *
 */
@Service
@Transactional
public class BaseSkillService {

	@Autowired
	private BaseSkillRepository baseSkillRepository;

	public List<BaseSkill> findAll() {
		return baseSkillRepository.findAll();
	}
}

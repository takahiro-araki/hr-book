package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.CommonSkill;
import com.example.demo.repository.CommonSkillRepository;

/**
 * @author yuma.watanabe
 *
 */
@Service
@Transactional
public class CommonSkillService {

	@Autowired
	private CommonSkillRepository commonSkillRepository;

	public List<CommonSkill> findAll() {
		return commonSkillRepository.findAll();
	}

}

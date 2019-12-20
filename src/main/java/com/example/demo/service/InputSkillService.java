package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.BaseSkill;
import com.example.demo.domain.CommonSkill;
import com.example.demo.domain.SubSkill;
import com.example.demo.repository.BaseSkillRepository;
import com.example.demo.repository.CommonSkillRepository;
import com.example.demo.repository.SubSkillRepository;

/**
 * @author yuma.watanabe
 *
 */
@Service
@Transactional
public class InputSkillService {

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

}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.SubSkill;
import com.example.demo.repository.SubSkillRepository;

/**
 * @author yuma.watanabe
 *
 */
@Service
@Transactional
public class SubSkillService {

	@Autowired
	private SubSkillRepository subSkillRepository;

	public List<SubSkill> findAll() {
		return subSkillRepository.findAll();
	}

}

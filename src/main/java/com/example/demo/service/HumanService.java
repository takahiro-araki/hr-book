package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Human;
import com.example.demo.repository.HumanRepository;

/**
 * エンジニアに関するデータを処理するサービスクラス.
 * 
 * @author morishimashun
 *
 */
@Transactional
@Service
public class HumanService {

	@Autowired
	private HumanRepository humanRepository;

	public Human load(Integer humanId) {
		return humanRepository.load(humanId);
	}

}

package com.example.demo.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Human;
import com.example.demo.form.ShowHumanListForm;
import com.example.demo.repository.HumanRepository;

/**
 * エンジニア情報リストを表示するサービスクラス.
 * 
 * @author takahiro.araki
 *
 */
@Service
public class ShowHumanListService {

	@Autowired
	private HumanRepository humanRepository;

	/**
	 * エンジニア情報を全件表示するメソッド（プリンシパルからユーザー情報を持ってくる処理未実装）
	 * 
	 * @param userRole
	 * @param userId
	 * @return
	 */
	public List<Human> showList(ShowHumanListForm form,Integer userRole, Integer userId) {
		try {
		List<Human> humanList = humanRepository.getList(form,userRole, userId);
		return humanList;}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Page<Human>showListPaging(int page,int size,List<Human> humanList){
	page--;
	int startHumanCount=page*size;
	List<Human>list;
	if(humanList.size()<startHumanCount) {
		list=Collections.emptyList();
	}else {
		int toIndex=Math.min(startHumanCount+size,humanList.size());
		list=humanList.subList(startHumanCount, toIndex);
	}
	Page<Human>humanPage=new PageImpl<Human>(list,PageRequest.of(page, size),humanList.size());
	return humanPage;	
	}

}

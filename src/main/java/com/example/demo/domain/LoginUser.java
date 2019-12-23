package com.example.demo.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * ユーザーのログイン情報を格納するエンティティ.
 * 
 * @author atsushi
 *
 */
public class LoginUser extends User{

	private static final long serialVersionUID = 1L;
	
	/** ユーザー情報 */
	private final com.example.demo.domain.User user;
	
	/**
	 * 通常のユーザー情報に加え、認可用ロールを設定する.
	 * 
	 * @param user ユーザー情報
	 * @param authorityList 権限情報が入ったリスト
	 */
	public LoginUser(com.example.demo.domain.User user, Collection<GrantedAuthority> authorityList) {
		super(user.getMailAddress(), user.getPassword(), authorityList);
		this.user = user;
	}
	
	public com.example.demo.domain.User getUser() {
		return user;
	}

}

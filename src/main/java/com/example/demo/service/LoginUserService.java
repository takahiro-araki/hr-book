package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
public class LoginUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
		User user = userRepository.findByMailAddress(mailAddress);
		if (user == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません");
		}
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		if (user.getUserRole() == 2) {
			authorityList.add(new SimpleGrantedAuthority("USER"));
		} else {
			authorityList.add(new SimpleGrantedAuthority("ADMIN"));
		}
		return new LoginUser(user, authorityList);
	}

	public void insert(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.insert(user);
	}

	public User findByMailAddress(String mailAddress) {
		return userRepository.findByMailAddress(mailAddress);
	}

}

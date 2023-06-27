package com.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userservice.entity.User;
import com.userservice.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository urepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		urepo.save(user);
		return "User added sucessfully";
	}

}

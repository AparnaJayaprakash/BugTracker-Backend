package com.jwtauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtauth.entity.Role;
import com.jwtauth.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role createNewRole(Role role) {
		
		return roleRepository.save(role);
		
	}

}

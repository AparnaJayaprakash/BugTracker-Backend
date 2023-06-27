package com.jwtauth.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauth.dto.UserRegistrationRequest;
import com.jwtauth.entity.UserEntity;
import com.jwtauth.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserService uservice;
	
	@PostConstruct
	public void initRoleAndUsers() {
		uservice.initRolesAndUser();
	}
	
	 @PostMapping({"/registerNewUser"})
	    public UserEntity registerNewUser(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest) {
	        return uservice.registerNewUser(userRegistrationRequest);
	    }
	    @PostMapping({"/registerNewQAndATeam"})
	    public UserEntity registerNewQAndATeam(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest) {
	        return uservice.registerNewQAndATeam(userRegistrationRequest);
	    }

	    @GetMapping({"/forAdmin"})
	    @PreAuthorize("hasRole('Admin')")
	    public String forAdmin(){
	        return "This URL is only accessible to the admin only";
	    }

	    @GetMapping({"/forUser"})
	    @PreAuthorize("hasRole('User')")
	    public String forUser(){
	        return "This URL is only accessible to the user only";
	    }

	    @GetMapping({"/forQAndATeam"})
	    @PreAuthorize("hasRole('QAndATeam')")
	    public String forKitchenstaff(){
	        return "This URL is only accessible to the QAndATeam only";
	    }

}

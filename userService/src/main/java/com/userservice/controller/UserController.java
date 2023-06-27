package com.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.entity.User;
import com.userservice.service.UserService;

@RestController
public class UserController {
	 	@Autowired
	    private UserService userService;

	    @PostMapping("/api/register")
	    public ResponseEntity<?> registerUser(@RequestBody User user) {
	        userService.addUser(user);
	        return ResponseEntity.ok().build();
	    }

}

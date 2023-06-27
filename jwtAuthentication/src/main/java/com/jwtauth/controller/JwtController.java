package com.jwtauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauth.entity.JwtRequest;
import com.jwtauth.entity.JwtResponse;
import com.jwtauth.service.JwtService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class JwtController {
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping({"/authenticate"})
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		return jwtService.createJwtToken(jwtRequest);

	}

}

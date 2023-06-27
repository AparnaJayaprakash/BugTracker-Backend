package com.jwtauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	
	private UserEntity user;            //this is taken because whenever we return token it will return the user details also, so that it is easy to handle in angular
	private String jwtToken;
	
	

}

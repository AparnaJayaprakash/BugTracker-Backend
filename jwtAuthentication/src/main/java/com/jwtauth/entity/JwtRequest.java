package com.jwtauth.entity;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class JwtRequest {
	
	 @NotEmpty(message = "Please enter your username")
	    private String userName;
	    @NotEmpty(message = "Please enter your password")
	    private String userPassword;
	
	

}

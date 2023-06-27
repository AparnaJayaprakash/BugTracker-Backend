package com.jwtauth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
	
	 @NotEmpty(message = "Username is required")
	    @Email(message = "Invalid email address")
	    @Pattern(regexp = ".*@gmail\\.com$", message = "Email must be of format @gmail.com")
	    private String userName;

	    @NotEmpty(message = "First name is required")
	    @Email(message = "Invalid email address")
	    @Pattern(regexp = ".*@gmail\\.com$", message = "Email must be of format @gmail.com")
	    private String email;

	    @NotEmpty(message = "Password is required")
//	    @ValidPassword(message = "Password criteria does not match")
	    private String userPassword;
	    
 

}

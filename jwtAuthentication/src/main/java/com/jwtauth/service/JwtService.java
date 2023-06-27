package com.jwtauth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtauth.entity.JwtRequest;
import com.jwtauth.entity.JwtResponse;
import com.jwtauth.entity.UserEntity;
import com.jwtauth.repository.UserRepository;
import com.jwtauth.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService{
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(userName, userPassword);
		
		final UserDetails userDetails =  loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		
		UserEntity user = urepo.findById(userName).get();
		
		return new JwtResponse(user, newGeneratedToken); 
		
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = urepo.findById(username).get();
		
		if(user != null) {
			
			 return new org.springframework.security.core.userdetails.User(
	                    user.getUserName(),
	                    user.getUserPassword(),
	                    getAuthority(user)
	            );
	        } else {
	            throw new UsernameNotFoundException("User not found with username: " + username);
	        }
		
	}
	
	private Set getAuthority(UserEntity user) {
		Set authorities = new HashSet<>();
		
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		});
		
		return authorities;
	}
	
	private void authenticate(String userName, String userPassword) throws Exception{
		
		try {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		
		}catch(DisabledException e) {
			throw new Exception("User is disabled",e);
		}catch(BadCredentialsException e) {
			throw new Exception("Bad credentials from user",e);
		}
		
	}
		

}

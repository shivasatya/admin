package com.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.AuthRequest;
import com.security.service.JwtService;

@RequestMapping("/auth")
@RestController
public class Auth {	

	@Autowired
	private JwtService jwtService; 
	
	/*
	 * @Autowired CustomUserDetailsService customUserDetailsService;
	 */
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	
	@PostMapping("/admin/login")
	public ResponseEntity<String> adminLoginPage(@RequestBody AuthRequest authRequest) {
	Authentication	authenticationManagerValue=		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
	boolean isAdmin = authenticationManagerValue.getAuthorities()
	        .stream()
	        .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
	if(authenticationManagerValue.isAuthenticated() && isAdmin ) {
	String token =	jwtService.generateToken(authRequest.getEmail(),authenticationManagerValue.getAuthorities());
	String username =	jwtService.extractUsername(token);
		return ResponseEntity.ok(token+ " ," + username);
	}
	else {
		throw new RuntimeException("ADMIN IS INVALID");
	}
		
	}
	
	
	

}

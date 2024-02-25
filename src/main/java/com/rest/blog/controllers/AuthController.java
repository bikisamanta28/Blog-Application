package com.rest.blog.controllers;

import com.rest.blog.exceptions.ApiException;
import com.rest.blog.payloads.JwtAuthRequest;
import com.rest.blog.payloads.JwtAuthResponse;
import com.rest.blog.security.JwtTokenHelper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse>createToken(@RequestBody JwtAuthRequest request)throws Exception{
		
		System.out.println("this is auth controller");
		
		this.authenticate(request.getUserName(),request.getPassword());
		
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUserName());
		
		String token=this.jwtTokenHelper.generateToken(userDetails);
		System.out.println("token in auth controller is "+token);
		
		
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
	
	private void authenticate (String username,String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
	
		try {
			this.authenticationManager.authenticate(authenticationToken);
			
		} catch (BadCredentialsException e) {
			throw new ApiException("Invalid username or password");
		}
			
		System.out.println("complete of authentication");
		
	}
}

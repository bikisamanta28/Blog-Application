package com.rest.blog.security;

import com.rest.blog.entities.User;
import com.rest.blog.exceptions.ResourceNotFoundException;
import com.rest.blog.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user =this.userRepo.findByEmail(userName).
				 orElseThrow(() -> new ResourceNotFoundException("User", "email: "+userName, 0));
		 
		return user;
	}

}

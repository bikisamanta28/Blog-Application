package com.rest.blog.services.userImpl;

import com.rest.blog.entities.User;
import com.rest.blog.payloads.UserDto;
import com.rest.blog.repositories.RoleRepo;
import com.rest.blog.repositories.UserRepo;
import com.rest.blog.services.UserService;
import com.rest.blog.exceptions.*;


import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	public UserDto getUserById(Integer userId) {
		
		User user =this.userRepo.findById(userId).
				 orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.userToDto(user);
	}

	public UserDto saveUsers(UserDto userdto) {
		 User user=this.dtoToUser(userdto);
		User usersUser= this.userRepo.save(user);
		return this.userToDto(usersUser);
	}

	public UserDto updateUser(UserDto userdto, Integer userId) {
	    
		User user =this.userRepo.findById(userId).
				 orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		user.setEmail(userdto.getEmail());
		user.setName(userdto.getName());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		
	   User newuser=this.userRepo.save(user);
	
		return this.userToDto(newuser);
	}

	public List<UserDto> getAllUser() {
		List<User> users =this.userRepo.findAll();
		
		List<UserDto>userdtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		
		return userdtos;
	}

	public void deleteUserById(int userId) {
		
		User user =this.userRepo.findById(userId).
				 orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userdto) {
		User user=this.modelMapper.map(userdto, User.class);
//		User user=new User();
//		user.setId(userdao.getId());
//		user.setEmail(userdao.getEmail());
//		user.setName(userdao.getName());
//		user.setPassword(userdao.getPassword());
//		user.setAbout(userdao.getAbout());

		return user;
	}
	
	private UserDto userToDto(User user) {
		
	UserDto userDto=this.modelMapper.map(user, UserDto.class);
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

	@Override
	public UserDto registerName(UserDto userdto) {
		User user=this.dtoToUser(userdto);
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//Role roleRepo=
		return null;
	}


}

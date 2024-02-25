package com.rest.blog.services;

import com.rest.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

	
	UserDto registerName(UserDto userdto);
	 UserDto getUserById(Integer userId);
	 UserDto saveUsers(UserDto users);
	 UserDto updateUser(UserDto users,Integer userId);
	 List<UserDto>getAllUser();
	 void deleteUserById(int userId);
}

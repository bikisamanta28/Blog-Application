package com.rest.blog.controllers;

import com.rest.blog.payloads.ApiResponse;
import com.rest.blog.payloads.UserDto;
import com.rest.blog.services.UserService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// POST-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto) {
         
		UserDto createUserDto = this.userService.saveUsers(userdto);

		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// PUT- update  user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updaterUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer id) {

	//	System.out.println("this is put method");
		UserDto getUserDto = this.userService.updateUser(userDto, id);

		return ResponseEntity.ok(getUserDto);
	}

	// Delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleterUser(@PathVariable("userId") Integer id) {

		this.userService.deleteUserById(id);

		// return new ResponseEntity (Map.of("message","user delete
		// successfully"),HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
	}

	// get All User
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {

		return ResponseEntity.ok(this.userService.getAllUser());
	}
	// get user

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getAllUser(@PathVariable("userId") Integer id) {

		return ResponseEntity.ok(this.userService.getUserById(id));
	}
}

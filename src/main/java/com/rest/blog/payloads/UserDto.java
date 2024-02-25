package com.rest.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	private Integer id;
	
	@NotEmpty(message = "Name cannot be null")
	@Size(min=4, message = "Username must be min of 4 characters")
	private String name;
	
	@Email(message = "email id is not valid")
	private String email;
	
	@NotEmpty(message = "password can not be empty")
	@Size(min =3,max=10,message = "password must be min of 3 charachter and maximum of 10 character")
	//@Pattern(regexp =)
	private String password;
	
	@NotEmpty(message = "about can not be empty")
	private String about;
	
}

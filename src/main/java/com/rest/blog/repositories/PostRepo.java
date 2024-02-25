package com.rest.blog.repositories;

import com.rest.blog.entities.Catagory;
import com.rest.blog.entities.Post;
import com.rest.blog.entities.User;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post>findByCatagory(Catagory catagory);
	
	List<Post>findByUser(User user);
	

}

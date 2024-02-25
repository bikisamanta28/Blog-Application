package com.rest.blog.repositories;

import com.rest.blog.entities.Comment;
import com.rest.blog.entities.Post;
import com.rest.blog.entities.User;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;





public interface CommentRepo extends JpaRepository<Comment, Integer>{

	
	Set<Comment>findByPost(Post post);
	
//	List<Comment>findByUser(User user);
	
	List<Comment>findByUsers(User users);
}

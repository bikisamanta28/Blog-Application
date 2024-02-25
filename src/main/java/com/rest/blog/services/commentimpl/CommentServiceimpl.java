package com.rest.blog.services.commentimpl;

import com.rest.blog.entities.Comment;
import com.rest.blog.entities.Post;
import com.rest.blog.entities.User;
import com.rest.blog.exceptions.ResourceNotFoundException;
import com.rest.blog.payloads.CommentDto;

import com.rest.blog.repositories.CommentRepo;
import com.rest.blog.repositories.PostRepo;
import com.rest.blog.repositories.UserRepo;
import com.rest.blog.services.CommentService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceimpl implements CommentService{

	@Autowired
	private CommentRepo commentrepo;
	
	@Autowired
	private UserRepo userRepo;
	
	 @Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CommentDto postComment(CommentDto commentDto, Integer postId,Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " user Id", userId));
		
		Post post=this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		
		Comment comments=this.modelmapper.map(commentDto, Comment.class);
		
		comments.setPost(post);
		comments.setUsers(user);
		
		CommentDto commentdto=this.modelmapper.map(this.commentrepo.save(comments),CommentDto.class);
		
		//Comment comment=this.commentrepo.save(this.modelmapper.map(commentDto, Comment.class));

		return commentdto;
	}

	@Override
	public List<CommentDto >getCommentByUser(Integer userId) {
		
		User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", " user Id", userId));
		List<CommentDto> commentdto=this.commentrepo.findByUsers(user).stream().map((comment)->this.modelmapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		return commentdto;
	}
	
	
	

}

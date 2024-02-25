package com.rest.blog.controllers;

import com.rest.blog.payloads.CommentDto;
import com.rest.blog.services.CommentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	CommentService commentService;

	@PostMapping("/post/{postId}/user/{userId}")
	public ResponseEntity<CommentDto>postComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId, @PathVariable Integer userId){
		
		CommentDto commentdto=this.commentService.postComment(commentDto, postId, userId);
		
		return new ResponseEntity<CommentDto>(commentdto,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<CommentDto>>getCommentByUser(@PathVariable Integer userId){
		
		List<CommentDto> commentDto=this.commentService.getCommentByUser(userId);
		
		return new ResponseEntity<List<CommentDto>>(commentDto,HttpStatus.OK);
	}
}

package com.rest.blog.services;

import com.rest.blog.payloads.CommentDto;

import java.util.List;


public interface CommentService {

 CommentDto postComment( CommentDto commentDto,Integer postId,Integer userId);

   List<CommentDto> getCommentByUser(Integer userId);
}

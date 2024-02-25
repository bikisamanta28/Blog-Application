package com.rest.blog.services;

import com.rest.blog.payloads.PostDto;
import com.rest.blog.payloads.PostResponse;

import java.util.List;



public interface PostService {

	
	PostDto savePost(PostDto postDto,Integer userId,Integer catId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String order);
	
	List<PostDto>getAllPostByCatagory(Integer catagoryId);
	
	List<PostDto>getAllPostByUserID(Integer userId);
	
	List<PostDto>getPostBySearch(String keyword);
	
	PostDto getPostByPostId(Integer postId);
	
	PostDto updatePostByPostId(PostDto postdto,Integer postId);
}

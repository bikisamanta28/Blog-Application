package com.rest.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDto {

	private Integer postId;

	private String postTitle;

	private String postContent;

	private String postImage;

	private UserDto user;

	private CatagoryDto catagory;

	private Date date;

	private Set<CommentDto> comments = new HashSet<>();

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CatagoryDto getCatagory() {
		return catagory;
	}

	public void setCatagory(CatagoryDto catagory) {
		this.catagory = catagory;
	}

}

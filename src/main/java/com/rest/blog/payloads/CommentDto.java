package com.rest.blog.payloads;


public class CommentDto {

   private Integer commnetId;
	
	private String comment;

	public Integer getCommnetId() {
		return commnetId;
	}

	public void setCommnetId(Integer commnetId) {
		this.commnetId = commnetId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}

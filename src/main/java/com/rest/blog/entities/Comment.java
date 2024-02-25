package com.rest.blog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "comments")
public class Comment {
	
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

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}



	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commnetId;
	
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User users;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
	

}

package com.rest.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "catagories")
public class Catagory {

	public Integer getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(Integer catagoryId) {
		this.catagoryId = catagoryId;
	}

	public String getCatagoryTitle() {
		return catagoryTitle;
	}

	public void setCatagoryTitle(String catagoryTitle) {
		this.catagoryTitle = catagoryTitle;
	}

	public String getCatagoryDescription() {
		return catagoryDescription;
	}

	public void setCatagoryDescription(String catagoryDescription) {
		this.catagoryDescription = catagoryDescription;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer catagoryId;
	
	@Column(name="title",length=100)
	private String catagoryTitle;
	
	

	@Column(name="description",length=200)
	private String catagoryDescription;

	@OneToMany(mappedBy = "catagory",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	List<Post>posts=new ArrayList<>();
	

	
}

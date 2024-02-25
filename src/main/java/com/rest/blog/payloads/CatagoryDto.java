package com.rest.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CatagoryDto {
	
	private Integer catagoryId;
	
	@NotBlank
	@Size(min=3)
	private String catagoryTitle;
	
	@NotBlank
	@Size(min=10)
	private String catagoryDescription;

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

	
	
	
	
	

	


}

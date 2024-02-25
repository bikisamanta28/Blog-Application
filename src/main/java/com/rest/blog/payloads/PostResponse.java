package com.rest.blog.payloads;

import java.util.List;

public class PostResponse {

	public List<PostDto> getContent() {
		return content;
	}

	public void setContent(List<PostDto> content) {
		this.content = content;
	}

	private List<PostDto>content;
	private int pageNumber;
	private int pageSize;
	 private int totalElement;
	 private int totalPages;
	  
	 private boolean lastpage;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(int totalElement) {
		this.totalElement = totalElement;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLastpage() {
		return lastpage;
	}

	public void setLastpage(boolean lastpage) {
		this.lastpage = lastpage;
	}
}

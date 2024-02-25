package com.rest.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	String resourceName;
	String fieldName;
	long fieldval;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldval) {
		super(String.format("%s not found with %s: %s", resourceName,fieldName,fieldval));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldval = fieldval;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public long getFieldval() {
		return fieldval;
	}
	public void setFieldval(long fieldval) {
		this.fieldval = fieldval;
	}
	
	

}

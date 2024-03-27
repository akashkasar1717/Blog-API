package com.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String resourcename;
	String filedname;
	long fieldvalue;

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public String getFiledname() {
		return filedname;
	}

	public void setFiledname(String filedname) {
		this.filedname = filedname;
	}

	public long getFieldvalue() {
		return fieldvalue;
	}

	public void setFieldvalue(long fieldvalue) {
		this.fieldvalue = fieldvalue;
	}

	public ResourceNotFoundException(String resourcename, String filedname, long fieldvalue) {
		super(String.format("%s not found with %s: %s", resourcename, filedname, fieldvalue));
		this.resourcename = resourcename;
		this.filedname = filedname;
		this.fieldvalue = fieldvalue;
	}

}

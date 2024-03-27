package com.blog.payloads;

import jakarta.validation.constraints.Size;

public class CategoryDto {

	private Integer Categoryid;
	@Size(min = 3, message = "Category Name is Must be minimum 3 character")
	private String categoryname;
	@Size(min = 7, message = "Description is Must be minimum 7 character")
	private String categorydiscription;

	public Integer getCategoryid() {
		return Categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		Categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCategorydiscription() {
		return categorydiscription;
	}

	public void setCategorydiscription(String categorydiscription) {
		this.categorydiscription = categorydiscription;
	}

}

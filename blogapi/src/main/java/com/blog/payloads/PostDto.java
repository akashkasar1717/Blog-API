package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDto {

	private Integer postid;
	private String posttitle;
	private String postcontent;
	private String postimagepath;
	private Date postdate;
	// Here We Added Post And category DTO is beacuse there is Recursivly
	// call category and post infinitely when we Are =going to create post
	private CategoryDto category;

	private UserDto user;

	private Set<CommentDto> comment = new HashSet<>();

	public Integer getPostid() {
		return postid;
	}

	public void setPostid(Integer postid) {
		this.postid = postid;
	}

	public String getPosttitle() {
		return posttitle;
	}

	public void setPosttitle(String posttitle) {
		this.posttitle = posttitle;
	}

	public String getPostcontent() {
		return postcontent;
	}

	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}

	public String getPostimagepath() {
		return postimagepath;
	}

	public void setPostimagepath(String postimagepath) {
		this.postimagepath = postimagepath;
	}

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Set<CommentDto> getComment() {
		return comment;
	}

	public void setComment(Set<CommentDto> comment) {
		this.comment = comment;
	}

}

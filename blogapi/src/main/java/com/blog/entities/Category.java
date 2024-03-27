package com.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "blog_category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "category_sqc", sequenceName = "category_sqc", allocationSize = 1)
	private Integer categoryid;
	private String categoryname;
	private String  categorydiscription;
	
	
	public Integer getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
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


	public List<Post> getPost() {
		return post;
	}


	public void setPost(List<Post> post) {
		this.post = post;
	}


	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<Post> post=new ArrayList<>();

	
	  
}

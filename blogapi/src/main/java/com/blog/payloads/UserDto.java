package com.blog.payloads;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 3, message = "Name Must be minimum 3 character")
	private String name;

	@Email
	private String email;

	@NotEmpty
	@Size(min = 4, max = 10, message = "Size Must be in 4 to 10 characters")
	private String password;

	@NotEmpty(message = "About Can't Be Empty")
	private String about;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

}

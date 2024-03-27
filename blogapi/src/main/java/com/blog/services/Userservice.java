package com.blog.services;

import java.util.List;

import com.blog.payloads.UserDto;

public interface Userservice {
	UserDto createUser(UserDto user);

	UserDto updatUser(UserDto user, Integer id);

	UserDto getByUserId(Integer id);

	List<UserDto> getAllUser();

	void deleteuser(Integer id);

}

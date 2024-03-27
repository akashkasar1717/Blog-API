package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.Userservice;

@Service
public class Userserviceimpl implements Userservice {

	private UserRepo urepo;

	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	public void setUrepo(UserRepo urepo) {
		this.urepo = urepo;
	}


	@Override
	public UserDto createUser(UserDto user) {
		User u = modelmapper.map(user, User.class);
		urepo.save(u);
		UserDto userdto = modelmapper.map(u, UserDto.class);
		return userdto;
	}

	@Override
	public UserDto updatUser(UserDto user, Integer id) {
		User u = urepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User id", id));
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setAbout(user.getAbout());
		u.setPassword(user.getPassword());
		User u1 = urepo.save(u);
		UserDto userdto = modelmapper.map(u1, UserDto.class);
		return userdto;
	}

	@Override
	public UserDto getByUserId(Integer id) {
		User u = urepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User id", id));
		UserDto userdto = modelmapper.map(u, UserDto.class);
		return userdto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> ulist = urepo.findAll();
		// We Used Stream api to conver listof users to listof userdto's
		List<UserDto> dtolist = ulist.stream().map(user -> this.modelmapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return dtolist;
	}

	@Override
	public void deleteuser(Integer id) {
		User u = urepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User id", id));
		urepo.delete(u);
	}

	/**
	 * The goal of ModelMapper is to make object mapping easy, by automatically
	 * determining how one object model maps to another, based on conventions, in
	 * the same way, that a human would - while providing a simple, refactoring-safe
	 * API for handling specific use cases.
	 */
}

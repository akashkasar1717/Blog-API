package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.UserDto;
import com.blog.services.impl.Userserviceimpl;

import jakarta.validation.Valid;

@RestController
public class Usercontroller {

	private Userserviceimpl uservice;

	@Autowired
	public void setUservice(Userserviceimpl uservice) {
		this.uservice = uservice;
	}

	@PostMapping("/user")
	public ResponseEntity<UserDto> createuser(@Valid @RequestBody UserDto userdto) {
		UserDto creteduser = uservice.createUser(userdto);
		return new ResponseEntity<UserDto>(creteduser, HttpStatus.CREATED);
	}

	@GetMapping("/user")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> userlist = uservice.getAllUser();
		return new ResponseEntity<List<UserDto>>(userlist, HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto> getuserByid(@PathVariable Integer id) {
		UserDto u = this.uservice.getByUserId(id);
		return new ResponseEntity<UserDto>(u, HttpStatus.OK);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto, @PathVariable Integer id) {
		UserDto u = this.uservice.updatUser(userdto, id);
		return new ResponseEntity<UserDto>(u, HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		this.uservice.deleteuser(id);
		return new ResponseEntity<String>("User Deleted successfully", HttpStatus.OK);
	}

}

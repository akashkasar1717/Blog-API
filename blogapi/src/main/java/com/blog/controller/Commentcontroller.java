package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CommentDto;
import com.blog.services.Commentservice;

@RestController
public class Commentcontroller {

	@Autowired
	private Commentservice cmtservice;

	@PostMapping("/post/{postid}/comment")
	public ResponseEntity<CommentDto> addcomment(@PathVariable Integer postid, @RequestBody CommentDto comment) {
		CommentDto commentdto = cmtservice.addcomment(comment, postid);
		return new ResponseEntity<CommentDto>(commentdto, HttpStatus.CREATED);
	}

	@DeleteMapping("/comment/{commentid}")
	public ResponseEntity<String> deletecomment(@PathVariable("commentid") int commentid) {
		System.out.println(commentid);
		cmtservice.deletecomment(commentid);
		return new ResponseEntity<String>("Comment Deleted Successfully...!", HttpStatus.OK);
	}
}

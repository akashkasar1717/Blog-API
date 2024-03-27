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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.blog.config.Appconstants;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

import com.blog.services.impl.Postserviceimpl;

@RestController
public class Postcontroller {

	private Postserviceimpl pservice;

	@Autowired
	public void setPservice(Postserviceimpl pservice) {
		this.pservice = pservice;
	}

	@PostMapping("/user/{userid}/category/{categoryid}/post")
	public ResponseEntity<PostDto> createpost(@RequestBody PostDto postdto, @PathVariable Integer userid,
			@PathVariable Integer categoryid) {
		PostDto p = this.pservice.createPost(postdto, userid, categoryid);
		return new ResponseEntity<PostDto>(p, HttpStatus.CREATED);
	}

	@GetMapping("/category/{categoryid}/post")
	public ResponseEntity<List<PostDto>> findbycategory(@PathVariable Integer categoryid) {
		List<PostDto> postDto = this.pservice.getPostsByCategory(categoryid);
		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
	}

	@GetMapping("/user/{userid}/post")
	public ResponseEntity<List<PostDto>> findbyuser(@PathVariable Integer userid) {
		List<PostDto> postDto = this.pservice.getPostsByUser(userid);
		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
	}

	// search post by title's keyword
	@GetMapping("/post/search/{key}")
	public ResponseEntity<List<PostDto>> searchbykeyword(@PathVariable String key) {
		List<PostDto> postDto = this.pservice.searchPosts(key);
		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
	}

	// All post Paging and sorting
	@GetMapping("/post")
	public ResponseEntity<PostResponse> findallpost(
			@RequestParam(value = "pageNumber", defaultValue = Appconstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Appconstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = Appconstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = Appconstants.SORT_DIR, required = false) String sortDir) {
		PostResponse postDto = this.pservice.getPosts(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postDto, HttpStatus.OK);
	}

	@GetMapping("/post/{postid}")
	public ResponseEntity<PostDto> findallpostByID(@PathVariable Integer postid) {
		PostDto postDto = this.pservice.getPostsById(postid);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	// Delete Post
	@DeleteMapping("/post/{postid}")
	public ResponseEntity<String> deletepostByID(@PathVariable Integer postid) {
		this.pservice.deletePostsById(postid);
		return new ResponseEntity<String>("Post deleted Succesfully..!", HttpStatus.OK);
	}

	// Update Post
	@PutMapping("/post/{postid}")
	public ResponseEntity<PostDto> updatepostByID(@RequestBody PostDto postdto, @PathVariable Integer postid) {
		PostDto pdto = this.pservice.updatePost(postdto, postid);
		return new ResponseEntity<PostDto>(pdto, HttpStatus.OK);
	}

}

package com.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

@Service
public interface Postservice {

	public PostDto createPost(PostDto postdto, Integer userid, Integer categoryid);

	public PostResponse getPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

//	List<PostDto> getPosts();
	public PostDto getPostsById(Integer postid);

	public PostDto updatePost(PostDto postdto, Integer postid);

	public void deletePostsById(Integer postid);

	// Get All Post By User
	List<PostDto> getPostsByUser(Integer userId);

	// Get All Post By Category
	List<PostDto> getPostsByCategory(Integer categoryId);

	// Search Post
	List<PostDto> searchPosts(String keyWord);

}

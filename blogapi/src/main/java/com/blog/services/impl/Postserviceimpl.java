package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.Postservice;

@Service
public class Postserviceimpl implements Postservice {

	private PostRepo prepo;

	@Autowired
	public void setPrepo(PostRepo prepo) {
		this.prepo = prepo;
	}

	@Autowired
	private ModelMapper modelmapper;

	private UserRepo urepo;

	@Autowired
	public void setUrepo(UserRepo urepo) {
		this.urepo = urepo;
	}

	private CategoryRepo crepo;

	@Autowired
	public void setCrepo(CategoryRepo crepo) {
		this.crepo = crepo;
	}

	@Override
	public PostDto createPost(PostDto postdto, Integer userid, Integer categoryid) {

		User u = urepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userid));
		Category c = crepo.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryid));

		Post p = modelmapper.map(postdto, Post.class);
		p.setPostimagepath("default.png");
		p.setPostdate(new Date());
		p.setUser(u);
		p.setCategory(c);

		prepo.save(p);

		return this.modelmapper.map(p, PostDto.class);
	}

	@Override
	public PostResponse getPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagepost = prepo.findAll(p);
		List<Post> plist = pagepost.getContent();

		List<PostDto> plistdto = plist.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postresponse = new PostResponse(plistdto, pagepost.getNumber(), pagepost.getSize(),
				pagepost.getTotalElements(), pagepost.isLast());

		return postresponse;
	}

	@Override
	public PostDto getPostsById(Integer postid) {

		Post post = prepo.findById(postid).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postid));
		PostDto postdto = this.modelmapper.map(post, PostDto.class);
		return postdto;
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postid) {

		Post post = prepo.findById(postid).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postid));

		post.setPosttitle(postdto.getPosttitle());
		post.setPostcontent(postdto.getPostcontent());
		post.setPostimagepath(postdto.getPostimagepath());

		prepo.save(post);

		PostDto pdto = this.modelmapper.map(post, PostDto.class);

		return pdto;

	}

	@Override
	public void deletePostsById(Integer postid) {

		prepo.findById(postid).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postid));
		prepo.deleteById(postid);
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User u = urepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));

		List<Post> plist = this.prepo.findByUser(u);
		List<PostDto> pdtolist = plist.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return pdtolist;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category c = crepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

		List<Post> plist = prepo.findByCategory(c);

		List<PostDto> pdtolist = plist.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return pdtolist;
	}

	@Override
	public List<PostDto> searchPosts(String keyWord) {

		List<Post> plist = prepo.searchByPosttitle("%" + keyWord + "%");
		List<PostDto> postdto = plist.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postdto;
	}

}

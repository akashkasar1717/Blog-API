package com.blog.services.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.Commentservice;

@Service
public class Commentserviceimpl implements Commentservice {

	@Autowired
	private CommentRepo cmtrepo;
	@Autowired
	private PostRepo prepo;
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CommentDto addcomment(CommentDto comment, Integer postid) {

		Post post = prepo.findById(postid).orElseThrow((() -> new ResourceNotFoundException("Post", "Postid", postid)));

		Comment c = modelmapper.map(comment, Comment.class);

		c.setPost(post);

		this.cmtrepo.save(c);

		CommentDto commentdto = this.modelmapper.map(c, CommentDto.class);

		return commentdto;
	}

	@Override
	@Transactional
	public void deletecomment(Integer commentid) {

		cmtrepo.findById(commentid)
				.orElseThrow((() -> new ResourceNotFoundException("comment", "commentid", commentid)));

		this.cmtrepo.deletebyid(commentid);
		System.out.println("delteded");
	}

}

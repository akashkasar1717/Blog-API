package com.blog.services;

import com.blog.payloads.CommentDto;

public interface Commentservice {

	CommentDto addcomment(CommentDto comment, Integer postid);

	void deletecomment(Integer commentid);
}

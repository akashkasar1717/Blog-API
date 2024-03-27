package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

	@Modifying
	@Query(nativeQuery = true, value = "delete from blog_comment where commentid =:commentid")
	void deletebyid(@Param("commentid") Integer commentid);

}

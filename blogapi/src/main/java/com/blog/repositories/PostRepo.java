package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	public List<Post> findByUser(User u);

	public List<Post> findByCategory(Category c);

	@Query(nativeQuery = true, value = "select * from blog_post p where p.posttitle like :key")
	public List<Post> searchByPosttitle(@Param("key") String key);

}

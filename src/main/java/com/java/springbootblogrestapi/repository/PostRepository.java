package com.java.springbootblogrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.springbootblogrestapi.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByCategoryId(Long categoryId);

}

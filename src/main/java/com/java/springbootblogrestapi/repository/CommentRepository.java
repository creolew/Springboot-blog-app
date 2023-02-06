package com.java.springbootblogrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.springbootblogrestapi.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long postId);
}

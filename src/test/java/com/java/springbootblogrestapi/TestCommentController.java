package com.java.springbootblogrestapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.java.springbootblogrestapi.entity.Comment;
import com.java.springbootblogrestapi.entity.Post;
import com.java.springbootblogrestapi.repository.CommentRepository;
import com.java.springbootblogrestapi.repository.PostRepository;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestCommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void testCreateComment() {
		Post newPost = new Post(null, "Title Test 20", "Description Test 20", "Content Test 20", null, null);
		
		Post savedPost = postRepository.save(newPost);
		
		
		//Comment comment= new Comment(0, "name test 2", "email_test2@gmail.com", "body test 2", savedPost);
		
		Comment comment = new Comment();
		
		comment.setName("aaa");
		comment.setEmail("bbb");
		comment.setBody("ccc");
		comment.setEmail("email_test2sss@gmail.com");
		comment.setPost(savedPost);
		
		Comment savedComment = commentRepository.save(comment);
		assertThat(savedComment).isNotNull();
	}
	
	@Test
	public void testGetCommentByPostId() {
		Post newPost = new Post(null, "Title Test huy", "Description Test huy", "Content Test huy", null, null);
		
		Post savedPost = postRepository.save(newPost);
		
		
		
		Comment comment = new Comment();
		
		comment.setName("huy");
		comment.setEmail("huy");
		comment.setBody("huy");
		comment.setEmail("huy@gmail.com");
		comment.setPost(savedPost);
		
		List<Comment> savedComment = commentRepository.saveAll(commentRepository.findByPostId(newPost.getId()));

		assertThat(savedComment).isNotNull();
		
		
	}
	
	@Test
	public void testGetCommentById() {
		Comment comment = commentRepository.findById((long) 2).get();
		assertThat(comment).isNotNull();
	}
	
	@Test
	public void testUpdateComment() {
		Comment comment = commentRepository.findById((long) 2).get();
		comment.setEmail("huy@gmail.com");
		commentRepository.save(comment);
	}
	
	@Test
	public void testDeleteComment() {
		commentRepository.deleteById((long)2);
	}
	

}

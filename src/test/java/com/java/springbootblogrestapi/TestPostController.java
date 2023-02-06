package com.java.springbootblogrestapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.java.springbootblogrestapi.entity.Category;
import com.java.springbootblogrestapi.entity.Post;
import com.java.springbootblogrestapi.payload.PostDto;
import com.java.springbootblogrestapi.repository.CategoryRepository;
import com.java.springbootblogrestapi.repository.PostRepository;
import com.java.springbootblogrestapi.service.PostService;


@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
 class TestPostController {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
	
	@Test
	public void testCreatePost() {
		
		Post newPost = new Post(null, "Title Test", "Description Test", "Content Test", null, null);
	
		Post savedPost = postRepository.save(newPost);
		//assertThat(newPost.getId()).isGreaterThan(0);
		assertEquals("Title Test", newPost.getTitle());
	}
	
	@Test
	public void testGetAllPosts() {
		Iterable<Post> listPosts = postRepository.findAll();
		listPosts.forEach(post -> System.out.println(post));
	}
	
	@Test
	public void testGetPostById() {
		
		Post postById = postRepository.findById((long) 1).get();
		System.out.println(postById);
		assertThat(postById).isNotNull();
		
	}
	
	@Test
	public void testUpdatePost() {
		Post postById = postRepository.findById((long) 1).get();
		postById.setDescription("Update description");
		postRepository.save(postById);
	}
	
	@Test
	public void testDeletePost() {
		Long postId =  (long) 1;
		postRepository.deleteById(postId);
		
	}
	
	@Test
	public void testGetPostByCategory() {
		
		Category category = new Category();
		category.setName("python");
		category.setDescription("python Category");
		
		categoryRepository.save(category);
		Post post = new Post(null, "Title Test 12", "Description Test 12", "Content Test 12", null, category);
		
		List<Post>  savedPost= postRepository.findByCategoryId(  category.getId() );

		postRepository.saveAll(savedPost);
		
		
		assertThat(savedPost ).isNotNull();
	
	}
	
	
	
}

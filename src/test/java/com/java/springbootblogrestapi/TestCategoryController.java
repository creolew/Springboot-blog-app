package com.java.springbootblogrestapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.java.springbootblogrestapi.entity.Category;
import com.java.springbootblogrestapi.entity.Post;
import com.java.springbootblogrestapi.repository.CategoryRepository;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestCategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testCreateCategory() {
		Category category = new Category();
		category.setName("name");
		category.setDescription("description");
		Category savedCategory = categoryRepository.save(category);
		assertThat(category.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testGetCategoryWithId() {
		Category category= categoryRepository.findById((long) 1).get();
		Category savedCategory = categoryRepository.save(category);
		assertThat(savedCategory).isNotNull();
	}
	
	@Test
	public void testGetAllCategories() {
		Iterable<Category> listCategories = categoryRepository.findAll();
		listCategories.forEach(post -> System.out.println(post));
	}
	
	@Test
	public void testUpdateCategory() {
		Category category = categoryRepository.findById((long) 1).get();
		
		category.setDescription("description");
	}
	
	@Test
	public void testDeleteCategory() {
		categoryRepository.deleteById((long) 9);
	}
	
	
	
	
	
}

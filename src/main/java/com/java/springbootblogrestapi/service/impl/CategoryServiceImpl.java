package com.java.springbootblogrestapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.java.springbootblogrestapi.entity.Category;
import com.java.springbootblogrestapi.entity.Post;
import com.java.springbootblogrestapi.exception.ResourceNotFoundException;
import com.java.springbootblogrestapi.payload.CategoryDto;
import com.java.springbootblogrestapi.payload.PostDto;
import com.java.springbootblogrestapi.repository.CategoryRepository;
import com.java.springbootblogrestapi.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryRepository;
	
	private ModelMapper modelMapper;
	
	
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}



	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {

		Category category = modelMapper.map(categoryDto, Category.class);
		
		Category savedCategory = categoryRepository.save(category);
		
		return modelMapper.map(savedCategory, CategoryDto.class);
	}



	@Override
	public CategoryDto getCategoryById(long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category", "id", categoryId) );
		return modelMapper.map(category, CategoryDto.class);
	}
	
	
	
	
//	@Override
//    public PostDto getPostById(long id) {
//        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
//        return mapToDTO(post);
//    }
	
	
	
	
	
	
	



	@Override
	public List<CategoryDto> getAllCategories() {

		List<Category> categories = categoryRepository.findAll();
		
		
		return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}



	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		category.setId(categoryId);
		
		Category updatedCategory= categoryRepository.save(category);

		return modelMapper.map(updatedCategory, CategoryDto.class);
	}



	@Override
	public void deleteCategory(Long categoryId) {
		Category category= categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		
		
		categoryRepository.delete(category);
	}
	
	
	

}

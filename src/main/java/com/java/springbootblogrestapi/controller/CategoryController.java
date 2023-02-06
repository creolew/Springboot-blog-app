package com.java.springbootblogrestapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.springbootblogrestapi.entity.Category;
import com.java.springbootblogrestapi.payload.CategoryDto;
import com.java.springbootblogrestapi.payload.PostDto;
import com.java.springbootblogrestapi.service.CategoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	//Build Add Category REST API
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer-key")
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto savedCategoryDto = categoryService.addCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(savedCategoryDto, HttpStatus.CREATED);
		
		
	}
	
	//Build Get Category REST API With Id
	@GetMapping("{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "id") Long categoryId){
		
		CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
	
		return ResponseEntity.ok(categoryDto);
	}
	
	
	
	
	
	
	
	//Build Get All Categories REST API
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getCategories(){
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	
	//Build Update Category REST API
	@PreAuthorize("hasRole('ADMIN')" )
	@PutMapping("{id}")
    @SecurityRequirement(name = "bearer-key")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, 
													  @PathVariable(name = "id") Long categoryId){
		return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
	}
	
	
	//Build Delete Category Rest API
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer-key")
	public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long categoryId){
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok("Category deleted successfully");
	}
	
}

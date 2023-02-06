package com.java.springbootblogrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.springbootblogrestapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}

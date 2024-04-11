package com.category_service.categoryservice.repository;

import com.category_service.categoryservice.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
}

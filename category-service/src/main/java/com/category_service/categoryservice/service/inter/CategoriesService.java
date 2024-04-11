package com.category_service.categoryservice.service.inter;

import com.category_service.categoryservice.entity.Categories;
import com.category_service.categoryservice.model.req.CategoryReq;
import com.category_service.categoryservice.model.res.CategoryRes;

import java.util.List;

public interface CategoriesService {

    public List<Categories> getCategoryPagination(int page, int size, String sortBy);

    public List<Categories> getAllCategories();

    public CategoryRes getCategoriesById(int id);

    public Categories createCategory(CategoryReq categories);

    public Categories updateCategory(int id,CategoryReq categories);

    public boolean deleteCategory(int id);
}

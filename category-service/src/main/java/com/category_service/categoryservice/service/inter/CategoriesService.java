package com.category_service.categoryservice.service.inter;

import com.category_service.categoryservice.entity.Categories;
import com.category_service.categoryservice.model.req.CategoryReq;
import com.category_service.categoryservice.model.res.CategoryRes;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoriesService {

    public String uploadFile(String fileName, MultipartFile file) throws IOException;
    public void displayFile(String fileCode , HttpServletResponse response) throws IOException;

    public List<Categories> getCategoryPagination(int page, int size, String sortBy);

    public List<Categories> getAllCategories();

    public CategoryRes getCategoriesById(int id);

    public Categories createCategory(CategoryReq categories);

    public Categories updateCategory(int id,CategoryReq categories);

    public boolean deleteCategory(int id);
}

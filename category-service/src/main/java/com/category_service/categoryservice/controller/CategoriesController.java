package com.category_service.categoryservice.controller;

import com.category_service.categoryservice.client.DetailCateClient;
import com.category_service.categoryservice.entity.Categories;
import com.category_service.categoryservice.model.req.CategoryReq;
import com.category_service.categoryservice.model.res.CategoryRes;
import com.category_service.categoryservice.service.inter.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    
    private final CategoriesService categoriesService;

    @GetMapping("/list")
    public ResponseEntity<List<Categories>> getAllCategories() {
        List<Categories> Categories =  categoriesService.getAllCategories();
        return ResponseEntity.ok().body(Categories);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Categories>> getCategoryPagination(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sortBy") String sortBy) {
        List<Categories> Categories =  categoriesService.getCategoryPagination(page, size, sortBy);
        return ResponseEntity.ok().body(Categories);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<CategoryRes> getDetailCategory(@PathVariable int id) {
        CategoryRes Category =  categoriesService.getCategoriesById(id);
        return ResponseEntity.ok().body(Category);
    }


        @PostMapping("/create")
    public ResponseEntity<Categories> createCategory(@RequestBody CategoryReq categoryReq) {

        Categories newCategories = categoriesService.createCategory(categoryReq);

        return ResponseEntity.ok().body(newCategories);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Categories> editCategory(@RequestParam("id") int id ,@RequestBody CategoryReq categoryReq) {

        Categories updateCategories = categoriesService.updateCategory(id, categoryReq);

        return  ResponseEntity.ok().body(updateCategories);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public boolean deleteCategory(@RequestParam("id") int id ) {

        boolean status = categoriesService.deleteCategory(id);

        return  ResponseEntity.ok().body(status).getBody();
    }
}

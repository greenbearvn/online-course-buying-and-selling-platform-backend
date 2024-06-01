package com.category_service.categoryservice.controller;

import com.category_service.categoryservice.client.DetailCateClient;
import com.category_service.categoryservice.entity.Categories;
import com.category_service.categoryservice.model.req.CategoryReq;
import com.category_service.categoryservice.model.res.CategoryRes;
import com.category_service.categoryservice.model.res.ImageRes;
import com.category_service.categoryservice.service.inter.CategoriesService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
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

    @PostMapping(value = "/uploadImage")
    public ImageRes uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String url = categoriesService.uploadFile(fileName,multipartFile);

        ImageRes imageRes = new ImageRes();
        imageRes.setData(url);

        return imageRes;
    }

    @GetMapping("/display/{fileCode}")
    public void displayImage(@PathVariable String fileCode, HttpServletResponse response) throws IOException {
        categoriesService.displayFile(fileCode, response);
    }


        @PostMapping("/create")
    public ResponseEntity<Categories> createCategory(@RequestBody CategoryReq categoryReq) {

        Categories newCategories = categoriesService.createCategory(categoryReq);

        return ResponseEntity.ok().body(newCategories);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Categories> editCategory(@PathVariable("id") int id ,@RequestBody CategoryReq categoryReq) {

        Categories updateCategories = categoriesService.updateCategory(id, categoryReq);

        return  ResponseEntity.ok().body(updateCategories);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public boolean deleteCategory(@PathVariable("id") int id ) {

        boolean status = categoriesService.deleteCategory(id);

        return  ResponseEntity.ok().body(status).getBody();
    }
}

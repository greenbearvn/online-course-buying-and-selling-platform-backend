package com.courseplus.blogservice.controller;

import com.courseplus.blogservice.entity.Blog;
import com.courseplus.blogservice.model.res.ImageRes;
import com.courseplus.blogservice.service.inter.BlogService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/list")
    public ResponseEntity<List<Blog>> getBlogsIsPublished() {
        List<Blog> items =  blogService.getBlogsIsPublished();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/list/new")
    public ResponseEntity<List<Blog>> getNewBlogs() {
        List<Blog> items =  blogService.getNewBlogs();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/list/categoryId/{categoryId}")
    public ResponseEntity<List<Blog>> getRecommendBlogs(@PathVariable int categoryId) {
        List<Blog> items =  blogService.getRecommendBlogs(categoryId);
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/detail/{id}")
    public Blog getDetail(@PathVariable int id) {
        Blog item = blogService.getDetail(id);
        return ResponseEntity.ok().body(item).getBody();

    }

    @PostMapping(value = "/uploadImage")
    public ImageRes uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String url = blogService.uploadFile(fileName,multipartFile);

        ImageRes imageRes = new ImageRes();
        imageRes.setData(url);

        return imageRes;
    }

    @GetMapping("/display/{fileCode}")
    public void displayImage(@PathVariable String fileCode, HttpServletResponse response) throws IOException {
        blogService.displayFile(fileCode, response);
    }

    @PostMapping("/create")
    public ResponseEntity<Blog> createCourses(@RequestBody Blog blog) {
        Blog newItem = blogService.create(blog);
        return ResponseEntity.ok().body(newItem);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Blog> editCourses(@PathVariable("id") int id ,@RequestBody Blog blog) {

        Blog updatedItem = blogService.update(id, blog);

        return  ResponseEntity.ok().body(updatedItem);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public boolean deleteCourses(@PathVariable("id") int id ) {

        boolean status = blogService.delete(id);

        return  ResponseEntity.ok().body(status).getBody();
    }

}

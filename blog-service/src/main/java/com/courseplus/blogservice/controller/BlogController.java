package com.courseplus.blogservice.controller;

import com.courseplus.blogservice.entity.Blog;
import com.courseplus.blogservice.service.inter.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

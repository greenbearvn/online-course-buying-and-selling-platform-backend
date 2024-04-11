package com.courseplus.blogservice.service.impl;

import com.courseplus.blogservice.entity.Blog;
import com.courseplus.blogservice.repository.BlogRepository;
import com.courseplus.blogservice.service.inter.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    @Override
    public List<Blog> getBlogsIsPublished() {
        return blogRepository.getBlogsIsPublished();
    }
}

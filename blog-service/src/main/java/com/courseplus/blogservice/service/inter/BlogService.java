package com.courseplus.blogservice.service.inter;

import com.courseplus.blogservice.entity.Blog;

import java.util.List;

public interface BlogService {
    public List<Blog> getBlogsIsPublished();
}

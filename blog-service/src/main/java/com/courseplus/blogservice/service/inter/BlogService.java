package com.courseplus.blogservice.service.inter;

import com.courseplus.blogservice.entity.Blog;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    public List<Blog> getBlogsIsPublished();

    public List<Blog> getAllByUserId(int id);

    public List<Blog> getNewBlogs();

    public List<Blog> getRecommendBlogs(int categoryId);

    public Blog getDetail(int id);

    public Blog create(Blog blog);

    public Blog update(int id,Blog blog);

    public boolean delete(int id);
    public String uploadFile(String fileName, MultipartFile file) throws IOException;

    public void displayFile(String fileCode, HttpServletResponse response) throws IOException;

}

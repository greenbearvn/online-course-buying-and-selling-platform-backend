package com.courseplus.blogservice.repository;

import com.courseplus.blogservice.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Integer> {

    @Query(value = "SELECT * FROM Blog p WHERE p.status > 0", nativeQuery = true)
    public List<Blog> getBlogsIsPublished();
}

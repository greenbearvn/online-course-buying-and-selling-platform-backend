package com.courseplus.blogservice.repository;

import com.courseplus.blogservice.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Integer> {

    @Query(value = "SELECT * FROM blog ORDER BY date_publish", nativeQuery = true)
    List<Blog> getNewBlog();

    List<Blog> findAllByCateid(int categoryid);

    List<Blog> findAllByUserId(int id);
}

package com.javabackend.commentservice.repository;

import com.javabackend.commentservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    public List<Comment> findAllByCourseId(int id);

    public List<Comment> findAllByOrderByCommentIdDesc();
}

package com.javabackend.commentservice.service.inter;

import com.javabackend.commentservice.entity.Comment;
import com.javabackend.commentservice.models.res.CommentRes;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CommentService {

    public Flux<CommentRes> getAllComment();

    public Flux<CommentRes> getCommentsByCourseId(int id);

    public Comment create(Comment comment);


    public boolean delete(int id);
}

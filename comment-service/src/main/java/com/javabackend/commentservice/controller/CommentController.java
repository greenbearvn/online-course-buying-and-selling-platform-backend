package com.javabackend.commentservice.controller;


import com.javabackend.commentservice.entity.Comment;
import com.javabackend.commentservice.models.res.CommentRes;
import com.javabackend.commentservice.service.inter.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @GetMapping("/list")
    public ResponseEntity<Flux<CommentRes>> getComments() {
        Flux<CommentRes> items =  commentService.getAllComment();
        return ResponseEntity.ok().body(items);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Flux<CommentRes>> getCommentsByCourseId(@PathVariable int id) {
        Flux<CommentRes> items =  commentService.getCommentsByCourseId(id);
        return ResponseEntity.ok().body(items);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteComment(@PathVariable int id) {
        return   commentService.delete(id);
    }

    @PostMapping("/create")
    public Comment create(@RequestBody Comment comment) {
        return  commentService.create(comment);
    }

}

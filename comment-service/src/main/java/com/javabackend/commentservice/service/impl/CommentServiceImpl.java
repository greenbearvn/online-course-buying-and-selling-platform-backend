package com.javabackend.commentservice.service.impl;

import com.javabackend.commentservice.entity.Comment;
import com.javabackend.commentservice.models.res.CommentRes;
import com.javabackend.commentservice.models.res.ProfileUserRes;
import com.javabackend.commentservice.models.res.UserRes;
import com.javabackend.commentservice.repository.CommentRepository;
import com.javabackend.commentservice.rest.inter.CommentRestService;
import com.javabackend.commentservice.service.inter.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRestService commentRestService;
    private final  CommentRepository commentRepository;

    @Override
    public Flux<CommentRes> getAllComment() {
        return Flux.defer(() -> {
            List<Comment> commentList = commentRepository.findAllByOrderByCommentIdDesc();

            return Flux.fromIterable(commentList)
                    .flatMap(comment -> {

                        Mono<ProfileUserRes> userResMono = commentRestService.getDetailUser(comment.getUserId());


                        return userResMono.map(userRes -> CommentRes.commentResBuilder(comment, userRes));
                    });
        });
    }

    @Override
    public Flux<CommentRes> getCommentsByCourseId(int id) {

        return Flux.defer(() -> {
            List<Comment> commentList = commentRepository.findAllByCourseId(id);

            return Flux.fromIterable(commentList)
                    .flatMap(comment -> {

                        Mono<ProfileUserRes> userResMono = commentRestService.getDetailUser(comment.getUserId());


                        return userResMono.map(userRes -> CommentRes.commentResBuilder(comment, userRes));
                    });
        });
    }

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public boolean delete(int id) {
        try{
            commentRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}

package com.javabackend.commentservice.models.res;


import com.javabackend.commentservice.entity.Comment;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRes {
    private Comment comment;
    private ProfileUserRes userRes;

    public static CommentRes commentResBuilder(Comment comment,ProfileUserRes userRes){
        return  CommentRes.builder().comment(comment).userRes(userRes).build();
    }
}

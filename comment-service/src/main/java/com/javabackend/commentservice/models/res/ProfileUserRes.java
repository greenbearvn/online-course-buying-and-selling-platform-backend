package com.javabackend.commentservice.models.res;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUserRes {
    private UserRes userRes;
    private Profile profile;
}

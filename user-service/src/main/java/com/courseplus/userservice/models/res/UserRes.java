package com.courseplus.userservice.models.res;

import com.courseplus.userservice.entity.User;
import com.courseplus.userservice.models.obj.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRes {


    @JsonProperty("userId")
    private int userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty( "email")
    private String email;

    @JsonProperty("role")
    private Role role;

    public static UserRes userResBuilder(User user){
        return UserRes.builder().
                userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(user.getRole()).build();


    }
}

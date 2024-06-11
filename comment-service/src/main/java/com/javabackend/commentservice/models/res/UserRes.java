package com.javabackend.commentservice.models.res;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.javabackend.commentservice.models.enums.Role;
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
}

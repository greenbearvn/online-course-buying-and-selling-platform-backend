package com.javabackend.orderservice.models.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javabackend.orderservice.models.obj.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

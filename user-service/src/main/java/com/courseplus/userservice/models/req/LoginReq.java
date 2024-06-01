package com.courseplus.userservice.models.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}

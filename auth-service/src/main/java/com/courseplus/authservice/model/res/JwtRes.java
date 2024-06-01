package com.courseplus.authservice.model.res;

import lombok.Data;

@Data
public class JwtRes {
    private String token;
    private String refreshToken;
}

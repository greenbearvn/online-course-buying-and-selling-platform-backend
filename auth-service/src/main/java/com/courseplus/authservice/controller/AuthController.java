package com.courseplus.authservice.controller;


import com.courseplus.authservice.model.req.AuthReq;
import com.courseplus.authservice.model.req.RefreshTokenReq;
import com.courseplus.authservice.model.res.JwtRes;
import com.courseplus.authservice.service.inter.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtRes> signIn(@RequestBody AuthReq authReq){
        return ResponseEntity.ok(authService.signIn(authReq));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtRes> refresh(@RequestBody RefreshTokenReq refreshTokenReq){
        return ResponseEntity.ok(authService.resfreshtToken(refreshTokenReq));
    }
}

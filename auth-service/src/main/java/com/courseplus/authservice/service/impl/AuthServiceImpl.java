package com.courseplus.authservice.service.impl;

import com.courseplus.authservice.model.req.AuthReq;
import com.courseplus.authservice.model.req.RefreshTokenReq;
import com.courseplus.authservice.model.res.JwtRes;
import com.courseplus.authservice.rest.inter.UserHttpService;
import com.courseplus.authservice.service.inter.AuthService;
import com.courseplus.authservice.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserHttpService userService;


    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;
    @Override
    public boolean signUp(AuthReq authReq) {
//        User user = new User();
//        user.setEmail(authReq.getEmail());
//        user.setPassword(passwordEncoder.encode(authReq.getPassword()));
//        user.setRole(Role.USER);

        return true;
    }

    @Override
    public JwtRes signIn(AuthReq authReq) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getEmail(),authReq.getPassword()));

        var user =  userService.findUserByEmail(authReq.getEmail()).block();

        var jwt = jwtTokenUtil.generateAccessToken(user);

        var refreshToken = jwtTokenUtil.generateRefreshToken(new HashMap<>(),user);

        JwtRes jwtRes = new JwtRes();

        jwtRes.setToken(jwt);
        jwtRes.setRefreshToken(refreshToken);

        return jwtRes;
    }

    @Override
    public JwtRes resfreshtToken(RefreshTokenReq refreshTokenReq) {

        String userEmail = jwtTokenUtil.extractUserName(refreshTokenReq.getToken());
        var user =  userService.findUserByEmail(userEmail).block();

        if(jwtTokenUtil.isTokenValid(refreshTokenReq.getToken(),user)){
            var jwt = jwtTokenUtil.generateAccessToken(user);
            JwtRes jwtRes = new JwtRes();

            jwtRes.setToken(jwt);
            jwtRes.setRefreshToken(refreshTokenReq.getToken());

            return jwtRes;

        }

        return null;
    }
}

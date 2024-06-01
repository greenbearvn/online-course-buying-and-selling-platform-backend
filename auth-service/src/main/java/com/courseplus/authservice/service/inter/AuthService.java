package com.courseplus.authservice.service.inter;

import com.courseplus.authservice.model.req.AuthReq;
import com.courseplus.authservice.model.req.RefreshTokenReq;
import com.courseplus.authservice.model.res.JwtRes;

public interface AuthService {
    public boolean signUp(AuthReq authReq);

    public JwtRes signIn(AuthReq authReq);

    public JwtRes resfreshtToken(RefreshTokenReq refreshTokenReq);

}

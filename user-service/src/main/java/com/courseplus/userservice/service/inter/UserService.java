package com.courseplus.userservice.service.inter;

import com.courseplus.userservice.entity.User;
import com.courseplus.userservice.models.req.LoginReq;
import com.courseplus.userservice.models.req.UserReq;

import java.util.Map;

public interface UserService {

    public User findUserByEmail(String email);

    public User createUser(UserReq userReq);

    public boolean Login(LoginReq loginReq);
    public boolean logOut();

    public Map getCurrentUser();
}

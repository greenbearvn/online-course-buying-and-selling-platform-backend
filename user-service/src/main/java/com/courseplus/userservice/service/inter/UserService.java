package com.courseplus.userservice.service.inter;

import com.courseplus.userservice.entity.User;
import com.courseplus.userservice.models.req.LoginReq;
import com.courseplus.userservice.models.req.UserReq;
import com.courseplus.userservice.models.res.ProfileUserRes;
import com.courseplus.userservice.models.res.UserRes;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface UserService {

    public User findUserByEmail(String email);

    public UserRes findUserById(int id);

    public Mono<ProfileUserRes> profileUserDetail(int id);

    public User createUser(UserReq userReq);

    public UserRes Login(LoginReq loginReq);
    public boolean logOut();

    public Map getCurrentUser();

    public List<UserRes> getAll();

    public boolean update(int id,User userReq);

    public boolean delete(int id);
}

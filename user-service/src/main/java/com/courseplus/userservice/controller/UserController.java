package com.courseplus.userservice.controller;


import com.courseplus.userservice.entity.User;
import com.courseplus.userservice.models.req.LoginReq;
import com.courseplus.userservice.models.req.UserReq;
import com.courseplus.userservice.models.res.ProfileUserRes;
import com.courseplus.userservice.models.res.UserRes;
import com.courseplus.userservice.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    private List<UserRes> getAll(){

        return userService.getAll();
    }

    @GetMapping("/email/{email}")
    private User getUserByEmail(@PathVariable String email){

        return userService.findUserByEmail(email);
    }

    @GetMapping("/id/{id}")
    private UserRes getUserById(@PathVariable int id){

        return userService.findUserById(id);
    }

    @GetMapping("/profile/{id}")
    private Mono<ProfileUserRes> getProfileUser(@PathVariable int id){

        return userService.profileUserDetail(id);
    }

    @PostMapping("/create")
    private User createUser(@RequestBody UserReq userReq){

        return userService.createUser(userReq);
    }


    @PutMapping("/update/{id}")
    private boolean updateUser(@PathVariable int id,@RequestBody User userReq){

        return userService.update(id,userReq);
    }

    @PostMapping("/login")
    private UserRes login(@RequestBody LoginReq loginReq){

        return userService.Login(loginReq);
    }

    @GetMapping("/getuser")
    private Map getUser(){

        return userService.getCurrentUser();
    }

    @PostMapping("/logut")
    private boolean logOut(){

        return userService.logOut();
    }

    @DeleteMapping("/delete/{id}")
    private boolean delete(@PathVariable int id){

        return userService.delete(id);
    }

}

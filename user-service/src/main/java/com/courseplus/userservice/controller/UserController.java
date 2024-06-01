package com.courseplus.userservice.controller;


import com.courseplus.userservice.entity.User;
import com.courseplus.userservice.models.req.LoginReq;
import com.courseplus.userservice.models.req.UserReq;
import com.courseplus.userservice.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/email/{email}")
    private User getUserByEmail(@PathVariable String email){

        return userService.findUserByEmail(email);
    }


    @PostMapping("/create")
    private User createUser(@RequestBody UserReq userReq){

        return userService.createUser(userReq);
    }

    @PostMapping("/login")
    private boolean login(@RequestBody LoginReq loginReq){

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

}

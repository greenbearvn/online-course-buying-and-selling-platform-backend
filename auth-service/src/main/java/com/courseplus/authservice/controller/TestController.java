package com.courseplus.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/abcxyz")
public class TestController {

    @GetMapping("/abc")
    public String testFunc(){
        return "abc";
    }
}

package com.javabackend.testedservice.controller;


import com.javabackend.testedservice.entity.Tested;
import com.javabackend.testedservice.models.res.TestedRes;
import com.javabackend.testedservice.service.inter.TestedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tested")
@RequiredArgsConstructor
public class TestedController {

    private final TestedService testedService;

    @GetMapping("/list")
    private List<TestedRes> list(){

        return testedService.listTest();
    }

    @GetMapping("/list/teacher/{id}")
    private List<TestedRes> getAllByCourseId(@PathVariable int id){

        return testedService.getAllByTeacherId(id);
    }

    @PostMapping("/create")
    private Tested create(@RequestBody Tested tested){

        return testedService.create(tested);
    }
}

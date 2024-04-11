package com.courseplus.testservice.controller;


import com.courseplus.testservice.entity.Test;
import com.courseplus.testservice.models.req.TestReq;
import com.courseplus.testservice.models.res.TestRes;
import com.courseplus.testservice.service.inter.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    
    private final TestService testService;

    @GetMapping("/list")
    public ResponseEntity<List<Test>> getAllCategories() {
        List<Test> items =  testService.getAllTest();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/list/video/{videoId}")
    public ResponseEntity<List<Test>> getAllTestByVideoId(@PathVariable  int videoId) {
        List<Test> items =  testService.getTestsbyVideoId(videoId);
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<TestRes> getDetailTest(@PathVariable int id) {

        TestRes item =  testService.getTestById(id);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping("/create")
    public ResponseEntity<Test> createTest(@RequestBody TestReq TestReq) {
        Test newItem = testService.createTest(TestReq);
        return ResponseEntity.ok().body(newItem);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Test> editTest(@RequestParam("id") int id ,@RequestBody TestReq TestReq) {

        Test updatedItem = testService.updateTest(id, TestReq);

        return  ResponseEntity.ok().body(updatedItem);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public boolean deleteTest(@RequestParam("id") int id ) {

        boolean status = testService.deleteTest(id);

        return  ResponseEntity.ok().body(status).getBody();
    }
    
}

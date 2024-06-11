package com.courseplus.testservice.controller;


import com.courseplus.testservice.entity.Test;
import com.courseplus.testservice.models.obj.Choice;
import com.courseplus.testservice.models.obj.Question;
import com.courseplus.testservice.models.req.TestReq;
import com.courseplus.testservice.models.res.TestRes;
import com.courseplus.testservice.models.res.TestsRes;
import com.courseplus.testservice.service.inter.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    
    private final TestService testService;

    @GetMapping("/list")
    public Flux<TestsRes> getAllTest() {
        return   testService.getAllTest();

    }

    @GetMapping("/list/teacher/{id}")
    public ResponseEntity<Flux<TestsRes>> getAllByTeacherId(@PathVariable("id") int teacherId) {
        Flux<TestsRes> items =  testService.getAllbyTeacherId(teacherId);
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

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean createTest(@RequestBody TestReq testReq) {
       return testService.createTest(testReq);
    }

    @PostMapping(path = "/create/test", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Choice> createTestdta(@RequestBody TestReq testReq) {
        List<Choice> allChoices = new ArrayList<>();
        for (Question question : testReq.getQuestions()) {
            allChoices.addAll(question.getChoices());
        }

        return allChoices;
    }

    @PutMapping("/edit/{id}")
    public boolean editTest(@PathVariable("id") int id ,@RequestBody TestReq TestReq) {

        return testService.updateTest(id, TestReq);

    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete/{id}")
    public boolean deleteTest(@PathVariable("id") int id ) {

        boolean status = testService.deleteTest(id);

        return  ResponseEntity.ok().body(status).getBody();
    }
    
}

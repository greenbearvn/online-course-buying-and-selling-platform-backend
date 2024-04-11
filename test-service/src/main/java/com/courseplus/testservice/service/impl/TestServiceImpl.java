package com.courseplus.testservice.service.impl;

import com.courseplus.testservice.entity.Test;
import com.courseplus.testservice.models.req.TestReq;
import com.courseplus.testservice.models.res.QuestionRes;
import com.courseplus.testservice.models.res.TestRes;
import com.courseplus.testservice.repository.TestRepository;
import com.courseplus.testservice.rest.inter.HttpService;
import com.courseplus.testservice.service.inter.TestService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final HttpService httpService;
    @Override
    public List<Test> getAllTest() {
        return testRepository.findAll();
    }

    @Override
    public TestRes getTestById(int id) {

        List<QuestionRes> questionRes = httpService.getQuestionsByTestId(id).collectList().block();
        Test res = testRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

        TestRes testRes = TestRes.testResBuider(res,questionRes);
        return testRes;
    }

    @Override
    public List<Test> getTestsbyVideoId(int videoId) {
        return testRepository.findAllByVideoId(videoId);
    }

    @Override
    public Test createTest(TestReq TestReq) {

        Test newTest = Test.builder()
                .testName(TestReq.getTestName())
                .teacherId(TestReq.getTeacherId())
                .videoId(TestReq.getVideoId()).build();
        return testRepository.save(newTest);

    }

    @Override
    public Test updateTest(int id, TestReq TestReq) {
        Test newTest = Test.builder()
                .testId(id)
                .testName(TestReq.getTestName())
                .teacherId(TestReq.getTeacherId())
                .videoId(TestReq.getVideoId()).build();
        return testRepository.save(newTest);
    }

    @Override
    public boolean deleteTest(int id) {
        Optional<Test> item = testRepository.findById(id);
        if(item.isPresent()){
            testRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

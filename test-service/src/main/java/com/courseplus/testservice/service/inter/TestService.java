package com.courseplus.testservice.service.inter;

import com.courseplus.testservice.entity.Test;
import com.courseplus.testservice.models.req.TestReq;
import com.courseplus.testservice.models.res.TestRes;
import com.courseplus.testservice.models.res.TestsRes;
import reactor.core.publisher.Flux;

import java.util.List;

public interface TestService {

    public Flux<TestsRes> getAllTest();

    public Flux<TestsRes> getAllbyTeacherId(int teacherId);

    public TestRes getTestById(int id);

    public List<Test> getTestsbyVideoId(int videoId);

    public boolean createTest(TestReq TestReq);

    public boolean updateTest(int id,TestReq TestReq);

    public boolean deleteTest(int id);
}

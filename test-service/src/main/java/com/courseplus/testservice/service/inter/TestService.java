package com.courseplus.testservice.service.inter;

import com.courseplus.testservice.entity.Test;
import com.courseplus.testservice.models.req.TestReq;
import com.courseplus.testservice.models.res.TestRes;

import java.util.List;

public interface TestService {

    public List<Test> getAllTest();

    public TestRes getTestById(int id);

    public List<Test> getTestsbyVideoId(int videoId);

    public Test createTest(TestReq TestReq);

    public Test updateTest(int id,TestReq TestReq);

    public boolean deleteTest(int id);
}

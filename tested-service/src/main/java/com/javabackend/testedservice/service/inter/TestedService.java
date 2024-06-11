package com.javabackend.testedservice.service.inter;

import com.javabackend.testedservice.entity.Tested;
import com.javabackend.testedservice.models.res.TestedRes;

import java.util.List;

public interface TestedService {

    public List<TestedRes> listTest();

    public Tested create(Tested tested);

    public List<TestedRes> getAllByTeacherId(int id);
}

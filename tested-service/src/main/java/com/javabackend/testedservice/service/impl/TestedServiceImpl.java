package com.javabackend.testedservice.service.impl;

import com.javabackend.testedservice.entity.Tested;
import com.javabackend.testedservice.models.res.Profile;
import com.javabackend.testedservice.models.res.Test;
import com.javabackend.testedservice.models.res.TestedRes;
import com.javabackend.testedservice.repository.TestedRepository;
import com.javabackend.testedservice.rest.inter.ProfileRestService;
import com.javabackend.testedservice.rest.inter.TestRestService;
import com.javabackend.testedservice.service.inter.TestedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TestedServiceImpl implements TestedService {

    private final ProfileRestService profileRestService;

    private final TestRestService testRestService;

    private final TestedRepository testedRepository;

    @Override
    public List<TestedRes> listTest() {


        List<Tested> testeds =  testedRepository.findAll();

        List<TestedRes> testedRes = testeds.stream().map(i ->{

            Profile profile = profileRestService.getDetailProfile(i.getUserId()).block();
            Test test = testRestService.getDetailTest(i.getTestedId()).block();

            return TestedRes.testResBuilder(i,test,profile);
        }).collect(Collectors.toList());

        return testedRes;
    }

    @Override
    public Tested create(Tested tested) {

        return testedRepository.save(tested);
    }

    @Override
    public List<TestedRes> getAllByTeacherId(int id) {
        List<Tested> testeds =  testedRepository.findAllByUserId(id);

        List<TestedRes> testedRes = testeds.stream().map(i ->{

            Profile profile = profileRestService.getDetailProfile(i.getUserId()).block();
            Test test = testRestService.getDetailTest(i.getTestId()).block();

            return TestedRes.testResBuilder(i,test,profile);
        }).collect(Collectors.toList());

        return testedRes;
    }


}

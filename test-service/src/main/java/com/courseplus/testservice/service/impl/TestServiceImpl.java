package com.courseplus.testservice.service.impl;

import com.courseplus.testservice.entity.Test;
import com.courseplus.testservice.models.event.TestCreateEvent;
import com.courseplus.testservice.models.obj.Choice;
import com.courseplus.testservice.models.obj.Question;
import com.courseplus.testservice.models.req.TestReq;
import com.courseplus.testservice.models.res.Profile;
import com.courseplus.testservice.models.res.QuestionRes;
import com.courseplus.testservice.models.res.TestRes;
import com.courseplus.testservice.models.res.TestsRes;
import com.courseplus.testservice.repository.TestRepository;
import com.courseplus.testservice.rest.inter.HttpService;
import com.courseplus.testservice.rest.inter.ProfileRestService;
import com.courseplus.testservice.service.inter.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {


    private String TEST_CREATE_TOPIC = "new-test";

    private String TEST_UPDATE_TOPIC = "update-test";

    private final TestRepository testRepository;
    private final HttpService httpService;

    private  final ObjectMapper objectMapper;

    private final NewTopic testCreate;

    private final KafkaTemplate<String,TestCreateEvent> kafkaTemplate;

    private final ProfileRestService profileRestService;

    @Override
    public Flux<TestsRes> getAllTest() {
        return Flux.defer(() -> {
            List<Test> tests = testRepository.findAll();

            return Flux.fromIterable(tests)
                    .flatMap(item -> {

                        Mono<Profile> profileMono = profileRestService.getProfileById(item.getTeacherId());


                        return profileMono.map(p -> TestsRes.testsRes(item, p));
                    });
        });
    }

    @Override
    public Flux<TestsRes> getAllbyTeacherId(int teacherId) {



        return Flux.defer(() -> {
            List<Test> tests = testRepository.findAllByTeacherId(teacherId);

            return Flux.fromIterable(tests)
                    .flatMap(item -> {

                        Mono<Profile> profileMono = profileRestService.getProfileById(item.getTeacherId());


                        return profileMono.map(p -> TestsRes.testsRes(item, p));
                    });
        });
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
    public boolean createTest(TestReq inputTestReq) {

        try{
            Test newTest = Test.builder()
                    .testName(inputTestReq.getTestName())
                    .teacherId(inputTestReq.getTeacherId())
                    .videoId(inputTestReq.getVideoId()).build();

            List<Choice> allChoices = new ArrayList<>();
            for (Question question : inputTestReq.getQuestions()) {
                allChoices.addAll(question.getChoices());
            }


            Optional<Test> test = testRepository.findById(inputTestReq.getTestId());
            if(test.isPresent()){
                sendDataQuestions(test.get(),inputTestReq);
            }
            else {
                newTest =  testRepository.save(newTest);
                sendDataQuestions(newTest,inputTestReq);
            }
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
    private void sendDataQuestions(Test newTest,TestReq testReq){
        TestCreateEvent createEvent = new TestCreateEvent();

        testReq.setTestId(newTest.getTestId());

        createEvent.setTestReq(testReq);

        kafkaTemplate.send(TEST_CREATE_TOPIC, createEvent);
    }



    @Override
    @Transactional
    public boolean updateTest(int id, TestReq TestReq) {

        try{

            Test test = testRepository.findById(id).orElseThrow();
            test.setTestName(TestReq.getTestName());
            test.setTeacherId(TestReq.getTeacherId());
            test.setVideoId(TestReq.getVideoId());

            testRepository.save(test);

            TestCreateEvent createEvent = new TestCreateEvent();


            createEvent.setTestReq(TestReq);

            kafkaTemplate.send(TEST_UPDATE_TOPIC, createEvent);
            return true;
        }
        catch (Exception e){
            return false;
        }

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

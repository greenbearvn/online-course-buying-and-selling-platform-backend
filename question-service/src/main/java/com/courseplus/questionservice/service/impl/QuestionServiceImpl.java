package com.courseplus.questionservice.service.impl;

import com.courseplus.questionservice.entity.Question;
import com.courseplus.questionservice.models.obj.Choice;
import com.courseplus.questionservice.models.res.QuestionRes;
import com.courseplus.questionservice.repository.QuestionRepository;
import com.courseplus.questionservice.rest.inter.HttpService;
import com.courseplus.questionservice.service.inter.QuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final HttpService httpService;
    @Override
    public List<QuestionRes> getQuestionsByTestId(int testId) {
        List<Question> questions = questionRepository.getQuestionByTestId(testId);

        List<QuestionRes> questionRes = questions.stream().map(p -> {
            List<Choice> choices = httpService.getChoicesByQuestionId(p.getQuestionId()).collectList().block();
            return QuestionRes.questionResBuider(p, choices); // Added return statement
        }).collect(Collectors.toList()); // Added terminal operation to collect the mapped elements into a List

        return questionRes; // Added return statement for the List
    }

    @Override
    public Optional<Question> getDetailQuestion(int testId) {
        return questionRepository.findById(testId);
    }

    @Override
    @Transactional
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(int id,Question quesReq) {
        Question question = questionRepository.findById(id).orElseThrow();
        question.setTestId(quesReq.getTestId());
        question.setQuestionId(quesReq.getQuestionId());
        question.setQuestionDescription(quesReq.getQuestionDescription());
        question.setSuggestion(quesReq.getSuggestion());

        return questionRepository.save(question);
    }


}

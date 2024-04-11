package com.courseplus.questionservice.controller;

import com.courseplus.questionservice.entity.Question;
import com.courseplus.questionservice.models.res.QuestionRes;
import com.courseplus.questionservice.rest.inter.HttpService;
import com.courseplus.questionservice.service.inter.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list/test/{id}")
    public ResponseEntity<List<QuestionRes>> getQuestionsByTestId(@PathVariable int id) {

        List<QuestionRes> items =  questionService.getQuestionsByTestId(id);
        return ResponseEntity.ok().body(items);
    }

    // chek question is correct data
    @GetMapping("/detail/{id}")
    public Question getDetail(@PathVariable int id) {

        Optional<Question> question = questionService.getDetailQuestion(id);
        if (question.isPresent()) {
            return question.get();
        }

        return null;
    }
}

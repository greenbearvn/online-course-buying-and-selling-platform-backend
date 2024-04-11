package com.courseplus.choiceservice.controller;

import com.courseplus.choiceservice.entity.Choice;
import com.courseplus.choiceservice.service.inter.ChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/choice")
@RequiredArgsConstructor
public class ChoiceController {

    private final ChoiceService choiceService;

    @GetMapping("/list/question/{questionId}")
    public List<Choice> getChoicesByQuestionId(@PathVariable int questionId) {
        return  choiceService.getChoicesByQuesionId(questionId);
    }

    @GetMapping("/detail/{choiceId}")
    public Choice getDetailChoice(@PathVariable int choiceId) {
        return  choiceService.getDetailChoice(choiceId);
    }

    @GetMapping("/check")
    public boolean checkChoiceCorrected(@RequestBody Choice choiceReq) {
        return choiceService.isCorrected(choiceReq);
    }
}

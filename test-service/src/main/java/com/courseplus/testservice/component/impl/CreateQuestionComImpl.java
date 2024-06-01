package com.courseplus.testservice.component.impl;

import com.courseplus.testservice.component.inter.CreateQuestionComInter;
import com.courseplus.testservice.models.obj.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CreateQuestionComImpl implements CreateQuestionComInter {
    @Override
    public Question createQuestion() {
        return null;
    }
}

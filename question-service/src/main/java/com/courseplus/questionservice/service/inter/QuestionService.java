package com.courseplus.questionservice.service.inter;

import com.courseplus.questionservice.entity.Question;
import com.courseplus.questionservice.models.res.QuestionRes;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    public List<QuestionRes> getQuestionsByTestId(int testId);

    public Optional<Question> getDetailQuestion(int testId);
}

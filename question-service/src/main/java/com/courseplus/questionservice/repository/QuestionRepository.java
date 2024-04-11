package com.courseplus.questionservice.repository;

import com.courseplus.questionservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    public List<Question> getQuestionByTestId(int testId);
}

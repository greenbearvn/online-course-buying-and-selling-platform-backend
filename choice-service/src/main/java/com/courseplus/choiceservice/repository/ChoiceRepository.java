package com.courseplus.choiceservice.repository;

import com.courseplus.choiceservice.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

    public List<Choice> findChoiceByQuestionId(int questionId);
}

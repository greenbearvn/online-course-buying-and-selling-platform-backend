package com.courseplus.choiceservice.service.inter;

import com.courseplus.choiceservice.entity.Choice;

import java.util.List;

public interface ChoiceService {

    public List<Choice> getChoicesByQuesionId(int quesionId);

    public Choice getDetailChoice(int choiceId);

    public boolean isCorrected(Choice choice);
}

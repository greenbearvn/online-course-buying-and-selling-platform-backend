package com.courseplus.choiceservice.service.impl;

import com.courseplus.choiceservice.entity.Choice;
import com.courseplus.choiceservice.model.obj.Question;
import com.courseplus.choiceservice.repository.ChoiceRepository;
import com.courseplus.choiceservice.rest.inter.HttpService;
import com.courseplus.choiceservice.service.inter.ChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChoiceServiceImpl implements ChoiceService {
    private final ChoiceRepository choiceRepository;
    private final HttpService httpService;
    @Override
    public List<Choice> getChoicesByQuesionId(int quesionId) {
        return choiceRepository.findChoiceByQuestionId(quesionId);
    }

    @Override
    public Choice getDetailChoice(int choiceId) {
        return choiceRepository.findById(choiceId).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public boolean isCorrected(Choice choiceReq) {
        Optional<Choice> choice = choiceRepository.findById(choiceReq.getChoiceId());

        if (choice.isPresent() && choice.get().getCorrected() == 1) {
            Question question = httpService.getDetailQuestion(choice.get().getQuestionId()).block();

            if (question != null) {
                return true;
            }
        }
        return false;
    }

}

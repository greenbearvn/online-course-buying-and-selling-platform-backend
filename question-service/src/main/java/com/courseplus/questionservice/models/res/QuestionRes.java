package com.courseplus.questionservice.models.res;

import com.courseplus.questionservice.entity.Question;
import com.courseplus.questionservice.models.obj.Choice;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class QuestionRes {
    @JsonProperty("questionId")
    private int questionId;

    @JsonProperty("testId")
    private int testId;

    @JsonProperty("questionDescription")
    private String questionDescription;

    @JsonProperty("suggestion")
    private String suggestion;

    private List<Choice> choices;

    public static QuestionRes questionResBuider(Question question, List<Choice> choices){
        return QuestionRes.builder()
                .questionId(question.getQuestionId())
                .testId(question.getTestId())
                .questionDescription(question.getQuestionDescription())
                .suggestion(question.getSuggestion())
                .choices(choices)
                .build();
    }
}

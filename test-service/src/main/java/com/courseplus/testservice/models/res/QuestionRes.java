package com.courseplus.testservice.models.res;


import com.courseplus.testservice.models.obj.Choice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}

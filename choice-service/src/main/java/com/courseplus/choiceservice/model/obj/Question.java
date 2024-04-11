package com.courseplus.choiceservice.model.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @JsonProperty("questionId")
    private int questionId;

    @JsonProperty("testId")
    private int testId;

    @JsonProperty("questionDescription")
    private String questionDescription;

    @JsonProperty("suggestion")
    private String suggestion;
}

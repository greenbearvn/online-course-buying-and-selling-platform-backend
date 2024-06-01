package com.courseplus.testservice.models.obj;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Question {

    @JsonProperty( "questionId")
    private int questionId;

    @JsonProperty("testId")
    private int testId;

    @JsonProperty( "questionDescription")
    private String questionDescription;

    @JsonProperty("suggestion")
    private String suggestion;

    @JsonProperty("choices")
    List<Choice> choices;
}

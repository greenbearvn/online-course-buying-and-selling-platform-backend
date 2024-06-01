package com.courseplus.testservice.models.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choice {

    @JsonProperty("choiceId")
    private int choiceId;

    @JsonProperty("questionId")
    private int questionId;

    @JsonProperty("choiceContent")
    private String choiceContent;

    @JsonProperty("corrected")
    private int corrected;
}

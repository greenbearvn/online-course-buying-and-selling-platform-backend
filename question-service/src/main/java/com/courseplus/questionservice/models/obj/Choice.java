package com.courseplus.questionservice.models.obj;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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

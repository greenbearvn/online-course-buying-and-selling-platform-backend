package com.courseplus.choiceservice.model.events;

import com.courseplus.choiceservice.entity.Choice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceCreateEvent {
    private Choice choice;
}

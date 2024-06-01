package com.courseplus.questionservice.models.events;

import com.courseplus.questionservice.models.obj.Choice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceCreateEvent {

    private Choice choice;
}

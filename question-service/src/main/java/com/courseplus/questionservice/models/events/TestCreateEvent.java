package com.courseplus.questionservice.models.events;

import com.courseplus.questionservice.models.req.TestReq;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCreateEvent {
    private TestReq testReq;
}

package com.courseplus.testservice.models.event;

import com.courseplus.testservice.models.req.TestReq;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCreateEvent {

    private TestReq testReq;
}

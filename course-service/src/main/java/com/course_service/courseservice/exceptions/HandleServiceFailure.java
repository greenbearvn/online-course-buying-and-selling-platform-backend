package com.course_service.courseservice.exceptions;

import com.course_service.courseservice.models.res.LessionRes;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class HandleServiceFailure {

    public List<LessionRes> handleServiceFailure(Throwable throwable) {
        // Handle the failure of the httpBinService (fallback or error handling)
        // For example, you can return an empty list or throw a custom exception
        return Collections.emptyList();
    }
}

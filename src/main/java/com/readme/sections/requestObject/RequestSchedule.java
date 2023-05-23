package com.readme.sections.requestObject;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RequestSchedule {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

package com.readme.sections.requestObject;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RequestSchedule {
    String name;
    LocalDateTime startDate;
    LocalDateTime endDate;
}

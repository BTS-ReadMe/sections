package com.readme.sections.projection;

import java.time.LocalDateTime;

public interface ISchedule {
    Long getId();
    String getName();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
}

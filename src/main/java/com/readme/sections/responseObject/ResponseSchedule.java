package com.readme.sections.responseObject;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseSchedule {
    Long id;
    String name;
    LocalDateTime startDate;
    LocalDateTime endDate;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class AdminSchedules {
        Long id;
        String name;
    }
}

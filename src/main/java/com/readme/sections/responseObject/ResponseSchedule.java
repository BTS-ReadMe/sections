package com.readme.sections.responseObject;

import com.readme.sections.dto.ScheduleDTO;
import com.readme.sections.service.ScheduleServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSchedule {
    private long id;
    private String name;
    private String startDate;
    private String endDate;

    public ResponseSchedule(ScheduleDTO scheduleDTO) {
        this.id = scheduleDTO.getId();
        this.name = scheduleDTO.getName();
        this.startDate = ScheduleServiceImpl.getUtcToKoreanTime(scheduleDTO.getStartDate());
        this.endDate = ScheduleServiceImpl.getUtcToKoreanTime(scheduleDTO.getEndDate());
    }
}

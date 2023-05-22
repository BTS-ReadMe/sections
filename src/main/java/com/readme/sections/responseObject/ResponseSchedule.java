package com.readme.sections.responseObject;

import com.readme.sections.dto.ScheduleDTO;
import com.readme.sections.service.ScheduleServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseSchedule {
    Long id;
    String name;
    String startDate;
    String endDate;

    public ResponseSchedule(ScheduleDTO scheduleDTO) {
        this.id = scheduleDTO.getId();
        this.name = scheduleDTO.getName();
        this.startDate = ScheduleServiceImpl.getUtcToKoreanTime(scheduleDTO.getStartDate());
        this.endDate = ScheduleServiceImpl.getUtcToKoreanTime(scheduleDTO.getEndDate());
    }
}

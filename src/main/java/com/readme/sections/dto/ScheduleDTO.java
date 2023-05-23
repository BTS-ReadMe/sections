package com.readme.sections.dto;

import com.readme.sections.model.Schedule;
import com.readme.sections.requestObject.RequestSchedule;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleDTO {
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ScheduleDTO(RequestSchedule requestSchedule) {
        this.name = requestSchedule.getName();
        this.startDate = requestSchedule.getStartDate();
        this.endDate = requestSchedule.getEndDate();
    }

    public ScheduleDTO(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.startDate = schedule.getStartDate();
        this.endDate = schedule.getEndDate();
    }

    public ScheduleDTO(Schedule schedule, ScheduleDTO scheduleDTO) {
        this.id = schedule.getId();
        this.name = scheduleDTO.getName() != null ? scheduleDTO.getName() :
            schedule.getName();
        this.startDate = scheduleDTO.getStartDate() != null ? scheduleDTO.getStartDate() :
            schedule.getStartDate();
        this.endDate = scheduleDTO.getEndDate() != null ? scheduleDTO.getEndDate() :
            schedule.getEndDate();
    }
}

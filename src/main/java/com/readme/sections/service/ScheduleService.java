package com.readme.sections.service;

import com.readme.sections.dto.ScheduleDTO;

public interface ScheduleService {
    public ScheduleDTO getSchedule(Long id);
    public void addSchedule(ScheduleDTO scheduleDTO);
    public ScheduleDTO existUpdateData(Long id, ScheduleDTO scheduleDTO);
    public void updateSchedule(ScheduleDTO scheduleDTO);
    public void deleteSchedule(Long id);
}

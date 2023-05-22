package com.readme.sections.service;

import com.readme.sections.dto.ScheduleDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    public ScheduleDTO getSchedule(Long id);
    public List<ScheduleDTO> getSchedules();
    public void addSchedule(ScheduleDTO scheduleDTO);
    public ScheduleDTO existUpdateData(Long id, ScheduleDTO scheduleDTO);
    public void updateSchedule(ScheduleDTO scheduleDTO);
    public void deleteSchedule(Long id);
    public String getUtcToKoreanTime(LocalDateTime utcTime);
}

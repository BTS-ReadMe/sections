package com.readme.sections.service;

import com.readme.sections.dto.ScheduleDTO;
import com.readme.sections.model.Schedule;
import com.readme.sections.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    @Override
    public void addSchedule(ScheduleDTO scheduleDTO) {
        scheduleRepository.save(Schedule.builder()
            .name(scheduleDTO.getName())
            .startDate(scheduleDTO.getStartDate())
            .endDate(scheduleDTO.getEndDate())
            .build());
    }
}

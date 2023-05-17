package com.readme.sections.service;

import com.readme.sections.dto.ScheduleDTO;
import com.readme.sections.model.Schedule;
import com.readme.sections.repository.ScheduleRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleDTO getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).get();
        return ScheduleDTO.builder()
            .id(schedule.getId())
            .name(schedule.getName())
            .startDate(schedule.getStartDate())
            .endDate(schedule.getEndDate())
            .build();
    }

    @Override
    public List<ScheduleDTO> getSchedules() {
        log.info(scheduleRepository.getCurrentSchedules().stream()
            .map(schedule -> ScheduleDTO.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .build())
            .collect(Collectors.toList()).toString());
        return scheduleRepository.getCurrentSchedules().stream()
            .map(schedule -> ScheduleDTO.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public void addSchedule(ScheduleDTO scheduleDTO) {
        scheduleRepository.save(Schedule.builder()
            .name(scheduleDTO.getName())
            .startDate(scheduleDTO.getStartDate())
            .endDate(scheduleDTO.getEndDate())
            .build());
    }

    @Override
    public ScheduleDTO existUpdateData(Long id, ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(id).get();
        return ScheduleDTO.builder()
            .id(schedule.getId())
            .name(scheduleDTO.getName() != null ? scheduleDTO.getName()
                : schedule.getName())
            .startDate(scheduleDTO.getStartDate() != null ? scheduleDTO.getStartDate()
                : schedule.getStartDate())
            .endDate(scheduleDTO.getEndDate() != null ? scheduleDTO.getEndDate()
                : schedule.getEndDate())
            .build();
    }

    @Override
    public void updateSchedule(ScheduleDTO scheduleDTO) {
        scheduleRepository.save(Schedule.builder()
            .id(scheduleDTO.getId())
            .name(scheduleDTO.getName())
            .startDate(scheduleDTO.getStartDate())
            .endDate(scheduleDTO.getEndDate())
            .build());
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public String getUtcToKoreanTime(LocalDateTime utcTime) {
        ZonedDateTime koreanTime = utcTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return formatter.format(koreanTime);
    }

}

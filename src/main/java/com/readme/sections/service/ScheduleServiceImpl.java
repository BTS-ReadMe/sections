package com.readme.sections.service;

import com.readme.sections.dto.ScheduleDTO;
import com.readme.sections.model.Schedule;
import com.readme.sections.repository.ScheduleRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    @Override
    public ScheduleDTO getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
        return new ScheduleDTO(schedule);
    }

    @Override
    public List<ScheduleDTO> getSchedules() {
        return scheduleRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDateTime.now(), LocalDateTime.now()).stream()
            .map(schedule -> new ScheduleDTO(schedule))
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void addSchedule(ScheduleDTO scheduleDTO) {
        scheduleRepository.save(new Schedule(scheduleDTO));
    }

    @Transactional(readOnly = true)
    @Override
    public ScheduleDTO existUpdateData(Long id, ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
        return new ScheduleDTO(schedule, scheduleDTO);
    }

    @Transactional
    @Override
    public void updateSchedule(ScheduleDTO scheduleDTO) {
        scheduleRepository.save(new Schedule(scheduleDTO));
    }

    @Transactional
    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public static String getUtcToKoreanTime(LocalDateTime utcTime) {
        ZonedDateTime koreanTime = utcTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return formatter.format(koreanTime);
    }

}

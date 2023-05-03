package com.readme.sections.controller;

import com.readme.sections.dto.ScheduleDTO;
import com.readme.sections.requestObject.RequestSchedule;
import com.readme.sections.responseObject.ResponseSchedule;
import com.readme.sections.service.ScheduleService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/schedules")
@RequiredArgsConstructor
@Slf4j
public class AdminScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSchedule> getSchedule(@PathVariable Long id) {
        ScheduleDTO scheduleDTO = scheduleService.getSchedule(id);
        return ResponseEntity.ok(ResponseSchedule.builder()
            .id(scheduleDTO.getId())
            .name(scheduleDTO.getName())
            .startDate(scheduleDTO.getStartDate())
            .endDate(scheduleDTO.getEndDate())
            .build());
    }

    @GetMapping
    public ResponseEntity<List<ResponseSchedule.AdminSchedules>> getSchedules() {
        return ResponseEntity.ok(scheduleService.getSchedules().stream()
            .map(schedule -> ResponseSchedule.AdminSchedules.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .build())
            .collect(Collectors.toList()));
    }

    @PostMapping
    public void addSchedule(@RequestBody RequestSchedule requestSchedule) {
        log.info(requestSchedule.getStartDate().toString());
        scheduleService.addSchedule(ScheduleDTO.builder()
            .name(requestSchedule.getName())
            .startDate(requestSchedule.getStartDate())
            .endDate(requestSchedule.getEndDate())
            .build());
    }

    @PatchMapping("/{id}")
    public void updateSchedule(@PathVariable Long id,
        @RequestBody RequestSchedule requestSchedule) {
        ScheduleDTO scheduleDTO = scheduleService.existUpdateData(id, ScheduleDTO.builder()
            .name(requestSchedule.getName())
            .startDate(requestSchedule.getStartDate())
            .endDate(requestSchedule.getEndDate())
            .build());
        scheduleService.updateSchedule(scheduleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }
}

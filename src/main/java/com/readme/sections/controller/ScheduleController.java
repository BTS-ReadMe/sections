package com.readme.sections.controller;

import com.readme.sections.responseObject.Response;
import com.readme.sections.responseObject.ResponseSchedule.Schedules;
import com.readme.sections.service.ScheduleService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/schedules")
@RequiredArgsConstructor
@Slf4j
public class ScheduleController {
    private final ScheduleService scheduleService;
    @GetMapping
    public ResponseEntity<Response> getSchedules() {
        return ResponseEntity.ok(Response.builder()
            .data(scheduleService.getSchedules().stream()
            .map(schedule -> Schedules.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .build())
            .collect(Collectors.toList()))
            .build());
    }
}

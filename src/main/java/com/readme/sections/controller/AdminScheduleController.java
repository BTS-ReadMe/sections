package com.readme.sections.controller;

import com.readme.sections.dto.ScheduleDTO;
import com.readme.sections.requestObject.RequestSchedule;
import com.readme.sections.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping
    public void addSchedule(@RequestBody RequestSchedule requestSchedule) {
        log.info(requestSchedule.getStartDate().toString());
        scheduleService.addSchedule(ScheduleDTO.builder()
            .name(requestSchedule.getName())
            .startDate(requestSchedule.getStartDate())
            .endDate(requestSchedule.getEndDate())
            .build());
    }
}

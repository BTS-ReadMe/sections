package com.readme.sections.controller;

import com.readme.sections.commonResponseObject.CommonDataResponse;
import com.readme.sections.responseObject.ResponseSchedule;
import com.readme.sections.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
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

    @Operation(summary = "진행 중인 스케줄 목록 조회", description = "진행 중인 스케줄 목록 조회", tags = {"스케줄"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<CommonDataResponse<List<ResponseSchedule>>> getSchedules() {
        return ResponseEntity.ok(new CommonDataResponse(
                scheduleService.getSchedules().stream()
                    .map(schedule -> new ResponseSchedule(schedule))
                    .collect(Collectors.toList())
            )
        );
    }
}

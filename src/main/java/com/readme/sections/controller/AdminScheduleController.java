package com.readme.sections.controller;

import com.readme.sections.dto.NovelCardsListAndScheduleDTO;
import com.readme.sections.dto.ScheduleDTO;
import com.readme.sections.requestObject.RequestSchedule;
import com.readme.sections.commonResponseObject.CommonDataResponse;
import com.readme.sections.responseObject.ResponseNovelCardsLIstAndSchedule;
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

    @Operation(summary = "스케줄 조회", description = "id에 해당하는 스케줄 조회", tags = {"Admin 스케줄"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommonDataResponse<ResponseSchedule>> getSchedule(@PathVariable Long id) {
        ScheduleDTO scheduleDTO = scheduleService.getSchedule(id);
        return ResponseEntity.ok(new CommonDataResponse(
            new ResponseSchedule(scheduleDTO)));
    }

    @GetMapping("/novels")
    public ResponseEntity<CommonDataResponse<List<ResponseNovelCardsLIstAndSchedule>>> getNovelCardsListAndSchedule() {
        List<NovelCardsListAndScheduleDTO> novelCardsListAndScheduleDTOList = scheduleService.getNovelCardsListBySchedule();
        return ResponseEntity.ok(new CommonDataResponse(
            novelCardsListAndScheduleDTOList.stream()
                .map(novelCardsListAndScheduleDTO -> new ResponseNovelCardsLIstAndSchedule(novelCardsListAndScheduleDTO))
                .collect(Collectors.toList())
        ));
    }

    @Operation(summary = "스케줄 전체 조회", description = "현재 진행 중인 스케줄 조회", tags = {"Admin 스케줄"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<CommonDataResponse<List<ResponseSchedule>>> getSchedules() {
        return ResponseEntity.ok(new CommonDataResponse(scheduleService.getSchedules().stream()
                .map(schedule -> new ResponseSchedule(schedule))
                .collect(Collectors.toList())
            )
        );
    }

    @Operation(summary = "스케줄 생성", description = "스케줄 생성", tags = {"Admin 스케줄"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void addSchedule(@RequestBody RequestSchedule requestSchedule) {
        log.info(requestSchedule.getStartDate().toString());
        scheduleService.addSchedule(new ScheduleDTO(requestSchedule));
    }

    @Operation(summary = "스케줄 수정", description = "RequestSchedule 필드 값 중 넘어온 값들을 확인하고 id에 해당하는 스케줄 수정", tags = {
        "Admin 스케줄"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PatchMapping("/{id}")
    public void updateSchedule(@PathVariable Long id,
        @RequestBody RequestSchedule requestSchedule) {
        ScheduleDTO scheduleDTO = scheduleService.existUpdateData(id,
            new ScheduleDTO(requestSchedule));
        scheduleService.updateSchedule(scheduleDTO);
    }

    @Operation(summary = "스케줄 삭제", description = "id에 해당하는 스케줄 삭제", tags = {"Admin 스케줄"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }
}

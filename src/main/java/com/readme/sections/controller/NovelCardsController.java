package com.readme.sections.controller;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.dto.NovelCardsDTO.Tag;
import com.readme.sections.dto.NovelCardsSliceDTO;
import com.readme.sections.responseObject.Response;
import com.readme.sections.responseObject.ResponseNovelCards;
import com.readme.sections.responseObject.ResponseNovelCardsSlice;
import com.readme.sections.service.NovelCardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cards/novels")
public class NovelCardsController {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int PAGE_SIZE;
    private final NovelCardsService novelCardsService;
    private final ModelMapper modelMapper;

    @Operation(summary = "소설 카드 조회", description = "id에 해당하는 에피소드 카드 조회", tags = {"소설 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> getNovelCard(@PathVariable Long id) {
        NovelCardsDTO novelCardsDTO = novelCardsService.getCards(id);
        return ResponseEntity.ok(Response.builder()
            .data(modelMapper.map(novelCardsDTO, ResponseNovelCards.class))
            .build());
    }

    @Operation(summary = "소설 카드 전체 조회", description = "소설 카드 전체 조회", tags = {"소설 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<Response> getAllNovelCardsByGere(
        @RequestParam(required=false) Integer pagination,
        @Param("genre") String genre,
        Pageable pageable) {
        if (pagination != null) {
            pageable = PageRequest.of(pagination, PAGE_SIZE);
        }
        NovelCardsSliceDTO novelCardsSliceDTO = new NovelCardsSliceDTO();
        novelCardsSliceDTO = novelCardsService.getAllCardsByGenre(genre, pageable);
        return ResponseEntity.ok(Response.builder()
            .data(ResponseNovelCardsSlice.builder()
                .novelCardsData(novelCardsSliceDTO.getNovelCardsData())
                .size(novelCardsSliceDTO.getSize())
                .page(novelCardsSliceDTO.getPage())
                .next(novelCardsSliceDTO.isNext())
                .build())
            .build());
    }

    @Operation(summary = "스케줄에 해당하는 소설 카드 목록 조회", description = "scheduleId에 해당하는 소설 카드 목록 조회", tags = {"소설 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/schedules")
    public ResponseEntity<Response> getNovelCardsForSchedule(
        @Param("scheduleId") Long scheduleId) {
        List<NovelCardsDTO> novelCardsDTOList = novelCardsService.getNovelCardsForSchedule(
            scheduleId);
        return ResponseEntity.ok(Response.builder()
            .data(novelCardsDTOList.stream()
                .map(novelCardsDTO -> ResponseNovelCards.builder()
                    .novelId(novelCardsDTO.getNovelId())
                    .title(novelCardsDTO.getTitle())
                    .author(novelCardsDTO.getAuthor())
                    .grade(novelCardsDTO.getGrade())
                    .genre(novelCardsDTO.getGenre())
                    .tags(novelCardsDTO.getTags().stream()
                        .map(element -> Tag.builder()
                            .id(element.getId())
                            .name(element.getName())
                            .build()).collect(Collectors.toList()))
                    .thumbnail(novelCardsDTO.getThumbnail())
                    .views(novelCardsDTO.getViews())
                    .serializationStatus(novelCardsDTO.getSerializationStatus())
                    .description(novelCardsDTO.getDescription())
                    .scheduleId(novelCardsDTO.getScheduleId())
                    .startDate(novelCardsDTO.getStartDate())
                    .starRating(novelCardsDTO.getStarRating())
                    .monday(novelCardsDTO.getMonday())
                    .tuesday(novelCardsDTO.getTuesday())
                    .wednesday(novelCardsDTO.getWednesday())
                    .thursday(novelCardsDTO.getThursday())
                    .friday(novelCardsDTO.getFriday())
                    .saturday(novelCardsDTO.getSaturday())
                    .sunday(novelCardsDTO.getSunday())
                    .isNew(novelCardsDTO.getIsNew())
                    .build())
                .collect(Collectors.toList()))
            .build());
    }
}

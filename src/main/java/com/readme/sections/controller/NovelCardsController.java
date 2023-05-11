package com.readme.sections.controller;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.dto.NovelCardsDTO.Tag;
import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.commonResponseObject.CommonDataResponse;
import com.readme.sections.responseObject.ResponseNovelCards;
import com.readme.sections.responseObject.ResponseNovelCardsPagination;
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
    public ResponseEntity<CommonDataResponse<ResponseNovelCards>> getNovelCard(
        @PathVariable Long id) {
        NovelCardsDTO novelCardsDTO = novelCardsService.getCards(id);
        return ResponseEntity.ok(new CommonDataResponse(
                modelMapper.map(novelCardsDTO, ResponseNovelCards.class)
            )
        );
    }

    @GetMapping("/test")
    public CommonDataResponse test(
        @RequestParam String serializationDays,
        @RequestParam Integer pagination
    ) {
        return new CommonDataResponse(
            novelCardsService.getAllCardsBySerializationDays(serializationDays, pagination));
    }

    @Operation(summary = "소설 카드 전체 조회", description = "소설 카드 전체 조회", tags = {"소설 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<CommonDataResponse<ResponseNovelCardsPagination>> getAllNovelCardsByGere(
        @RequestParam(required = false) Integer pagination,
        @RequestParam String category,
        @RequestParam String subCategory) {
        NovelCardsPaginationDTO novelCardsPaginationDTO;
        if (category.equals("요일")) {
            novelCardsPaginationDTO = novelCardsService.getAllCardsBySerializationDays(subCategory,
                pagination);
        } else {
            if (subCategory.equals("신작")) {
                novelCardsPaginationDTO = novelCardsService.getNewNovelsByGenre(category, pagination);
            } else {
                novelCardsPaginationDTO = novelCardsService.getAllCardsByGenre(category,
                    subCategory, pagination);
            }

        }
        return ResponseEntity.ok(new CommonDataResponse(ResponseNovelCardsPagination.builder()
            .novelCardsData(novelCardsPaginationDTO.getNovelCardsData())
            .page(novelCardsPaginationDTO.getPage())
            .size(novelCardsPaginationDTO.getSize())
            .totalElements(novelCardsPaginationDTO.getTotalElements())
            .totalPages(novelCardsPaginationDTO.getTotalPages())
            .build()
        ));
    }

    @GetMapping("/search")
    public ResponseEntity<CommonDataResponse> searchTags(
        @RequestParam String tags
    ) {
        return ResponseEntity.ok(new CommonDataResponse(novelCardsService.searchTags(tags)));
    }

    @Operation(summary = "스케줄에 해당하는 소설 카드 목록 조회", description = "scheduleId에 해당하는 소설 카드 목록 조회", tags = {
        "소설 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/schedules")
    public ResponseEntity<CommonDataResponse<List<ResponseNovelCards>>> getNovelCardsForSchedule(
        @Param("scheduleId") Long scheduleId) {
        List<NovelCardsDTO> novelCardsDTOList = novelCardsService.getNovelCardsForSchedule(
            scheduleId);
        return ResponseEntity.ok(new CommonDataResponse(
                novelCardsDTOList.stream()
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
                        .episodeCount(novelCardsDTO.getEpisodeCount())
                        .build())
                    .collect(Collectors.toList())
            )
        );
    }
}

package com.readme.sections.controller;

import com.readme.sections.dto.NovelCardsViewDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.commonResponseObject.CommonDataResponse;
import com.readme.sections.responseObject.ResponseNovelCards;
import com.readme.sections.responseObject.ResponseNovelCardsPagination;
import com.readme.sections.service.KafkaProducer;
import com.readme.sections.service.NovelCardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final KafkaProducer kafkaProducer;

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
        return ResponseEntity.ok(new CommonDataResponse(
                new ResponseNovelCards(novelCardsService.getCards(id))
            )
        );
    }

    @Operation(summary = "소설 카드 카테고리 조회", description = "장르 및 요일, 연재 상태에 따른 소설 카드 조회", tags = {"소설 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<CommonDataResponse<ResponseNovelCardsPagination>> getAllNovelCardsByGenre(
        @RequestParam(required = false) Integer pagination,
        @RequestParam String category,
        @RequestParam String subCategory) {
        NovelCardsPaginationDTO novelCardsPaginationDTO;
        if (category.equals("요일")) {
            novelCardsPaginationDTO = novelCardsService.getAllCardsBySerializationDays(subCategory,
                pagination);
        } else if(category.equals("전체")) {
            novelCardsPaginationDTO = novelCardsService.getAllCards(pagination);
        }
        else {
            if (subCategory.equals("신작")) {
                novelCardsPaginationDTO = novelCardsService.getNewNovelsByGenre(category,
                    pagination);
            } else {
                novelCardsPaginationDTO = novelCardsService.getAllCardsByGenre(category,
                    subCategory, pagination);
            }

        }
        return ResponseEntity.ok(new CommonDataResponse(new ResponseNovelCardsPagination(novelCardsPaginationDTO)
        ));
    }

    @Operation(summary = "소설 카드 검색", description = "title or tag로 검색: title은 글자가 포함되어도 검색되고, tag는 일치해야 검색 됨", tags = {
        "소설 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/search")
    public ResponseEntity<CommonDataResponse<ResponseNovelCardsPagination>> searchKeyword(
        @RequestParam String keyword,
        @RequestParam(required = false) Integer pagination
    ) {
        kafkaProducer.searchCount(keyword);
        return ResponseEntity.ok(
            new CommonDataResponse(
                new ResponseNovelCardsPagination(novelCardsService.searchNovelCards(keyword, pagination))));
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
        List<NovelCardsViewDTO> novelCardsViewDTOList = novelCardsService.getNovelCardsForSchedule(
            scheduleId);
        return ResponseEntity.ok(new CommonDataResponse(
                novelCardsViewDTOList.stream()
                    .map(novelCardsViewDTO -> new ResponseNovelCards(novelCardsViewDTO))
                    .collect(Collectors.toList())
            )
        );
    }
}

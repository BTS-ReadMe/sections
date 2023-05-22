package com.readme.sections.controller;

import com.readme.sections.dto.NovelCardsEntityDTO;
import com.readme.sections.service.NovelCardsService;
import com.readme.sections.requestObject.RequestNovelCards;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/cards/novels")
@Slf4j
public class AdminNovelCardsController {
    private final NovelCardsService novelCardsService;

    @Operation(summary = "소설 카드 등록", description = "소설 카드 등록", tags = {"Admin 소설 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void addNovelCard(@RequestBody RequestNovelCards requestNovelCards) {
        novelCardsService.addCards(new NovelCardsEntityDTO(requestNovelCards));
    }

    @Operation(summary = "소설 카드 수정", description = "RequestNovelCards 필드 값 중 넘어온 값들을 확인하고 id에 해당하는 소설 카드 수정", tags = {"Admin 소설 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PatchMapping("/{id}")
    public void updateNovelCard(@PathVariable Long id, @RequestBody RequestNovelCards requestNovelCards) {
        novelCardsService.updateNovelCardsDTO(novelCardsService.updateNovelCardsDTO(id, new NovelCardsEntityDTO(requestNovelCards)));
    }

    @Operation(summary = "소설 카드 삭제", description = "id에 해당하는 소설 카드 삭제", tags = {"Admin 소설 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/{id}")
    public void deleteNovelCard(@PathVariable Long id) {
        novelCardsService.deleteCards(id);
    }

}

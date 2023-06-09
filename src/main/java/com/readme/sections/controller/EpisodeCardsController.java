package com.readme.sections.controller;

import com.readme.sections.dto.EpisodeCardsDTO;
import com.readme.sections.commonResponseObject.CommonDataResponse;
import com.readme.sections.responseObject.ResponseEpisodeCards;
import com.readme.sections.service.EpisodeCardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cards/episodes")
@RequiredArgsConstructor
public class EpisodeCardsController {

    private final EpisodeCardsService episodeCardsService;

    @Operation(summary = "에피소드 목록 정보가 담긴 에피소드 카드 조회", description = "id에 해당하는 에피소드 목록 정보가 담긴 에피소드 카드 조회", tags = {
        "에피소드 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommonDataResponse<ResponseEpisodeCards>> getEpisodeCard(
        @PathVariable Long id,
        @RequestParam(required = false) Integer pagination) {
        EpisodeCardsDTO episodeCardsDTO = episodeCardsService.getCards(id, pagination);
        return ResponseEntity.ok(new CommonDataResponse(ResponseEpisodeCards.builder()
                .novelId(episodeCardsDTO.getNovelId())
                .episodes(episodeCardsDTO.getEpisodes())
                .page(episodeCardsDTO.getPage())
                .size(episodeCardsDTO.getSize())
                .totalElements(episodeCardsDTO.getTotalElements())
                .totalPages(episodeCardsDTO.getTotalPages())
                .build()
            )
        );
    }
}

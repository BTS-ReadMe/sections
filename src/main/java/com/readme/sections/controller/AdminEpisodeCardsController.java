package com.readme.sections.controller;

import com.readme.sections.dto.EpisodeCardsDTO;
import com.readme.sections.requestObject.RequestEpisodeCards;
import com.readme.sections.service.EpisodeCardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/cards/episodes")
public class AdminEpisodeCardsController {

    private final EpisodeCardsService episodeCardsService;
    private final ModelMapper modelMapper;

    @Operation(summary = "에피소드 카드 등록", description = "에피소드 카드 등록", tags = {"Admin 에피소드 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void addEpisodeCard(@RequestBody RequestEpisodeCards requestEpisodeCards) {
        EpisodeCardsDTO episodeCardsDTO = modelMapper.map(requestEpisodeCards, EpisodeCardsDTO.class);
        episodeCardsService.addCards(episodeCardsDTO);
    }

    @Operation(summary = "에피소드 카드 수정", description = "RequestEpisodeCards 필드 값 중 넘어온 값들을 확인하고 id에 해당하는 에피소드 카드 수정", tags = {"Admin 에피소드 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PatchMapping("/{id}")
    public void updateEpisodeCard(@PathVariable Long id, @RequestBody RequestEpisodeCards requestEpisodeCards) {
        EpisodeCardsDTO episodeCardsDTO = modelMapper.map(requestEpisodeCards, EpisodeCardsDTO.class);
        episodeCardsService.updateCards(episodeCardsService.existUpdateData(id, episodeCardsDTO));
    }

    @Operation(summary = "에피소드 카드 삭제", description = "id에 해당하는 에피소드 카드 삭제", tags = {"Admin 에피소드 카드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/{id}")
    public void deleteEpisodeCard(@PathVariable Long id) {
        episodeCardsService.deleteCards(id);
    }


}

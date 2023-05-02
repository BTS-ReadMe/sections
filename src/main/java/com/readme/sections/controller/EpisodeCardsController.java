package com.readme.sections.controller;

import com.readme.sections.model.EpisodeCards;
import com.readme.sections.responseObject.ResponseEpisodeCards;
import com.readme.sections.service.EpisodeCardsService;
import com.readme.sections.service.EpisodeCardsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cards/episodes")
@RequiredArgsConstructor
public class EpisodeCardsController {
    private final EpisodeCardsService episodeCardsService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEpisodeCards> getEpisodeCard(@PathVariable Long id) {
        EpisodeCards episodeCards = episodeCardsService.getCards(id);
        return ResponseEntity.ok(ResponseEpisodeCards.builder()
            .novelId(episodeCards.getNovelId())
            .episodes(episodeCards.getEpisodes())
            .build());
    }
}

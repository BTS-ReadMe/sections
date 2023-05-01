package com.readme.sections.controller;

import com.readme.sections.dto.EpisodeCardsDTO;
import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.requestObject.RequestEpisodeCards;
import com.readme.sections.requestObject.RequestNovelCards;
import com.readme.sections.service.EpisodeCardsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    private final EpisodeCardsServiceImpl episodeCardsServiceImpl;
    private final ModelMapper modelMapper;


    @PostMapping
    public void addEpisodeCard(@RequestBody RequestEpisodeCards requestEpisodeCards) {
        EpisodeCardsDTO episodeCardsDTO = modelMapper.map(requestEpisodeCards, EpisodeCardsDTO.class);
        episodeCardsServiceImpl.addCards(episodeCardsDTO);
    }

    @PatchMapping("/{id}")
    public void updateEpisodeCard(@PathVariable Long id, @RequestBody RequestEpisodeCards requestEpisodeCards) {
        EpisodeCardsDTO episodeCardsDTO = modelMapper.map(requestEpisodeCards, EpisodeCardsDTO.class);
        episodeCardsServiceImpl.updateCards(episodeCardsServiceImpl.existUpdateData(id, episodeCardsDTO));
    }


}

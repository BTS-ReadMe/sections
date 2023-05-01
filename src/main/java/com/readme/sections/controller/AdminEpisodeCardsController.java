package com.readme.sections.controller;

import com.readme.sections.dto.EpisodeCardsDTO;
import com.readme.sections.requestObject.RequestEpisodeCards;
import com.readme.sections.service.EpisodeCardsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminEpisodeCardsController {

    private final EpisodeCardsServiceImpl episodeCardsServiceImpl;
    private final ModelMapper modelMapper;


    @PostMapping
    public void addEpisodeCard(@RequestBody RequestEpisodeCards requestEpisodeCards) {
        EpisodeCardsDTO episodeCardsDTO = modelMapper.map(requestEpisodeCards, EpisodeCardsDTO.class);
        episodeCardsServiceImpl.addCards(episodeCardsDTO);
    }


}

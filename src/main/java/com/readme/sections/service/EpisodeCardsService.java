package com.readme.sections.service;

import com.readme.sections.dto.EpisodeCardsDTO;
import com.readme.sections.dto.NovelCardsDTO;

public interface EpisodeCardsService {
    public void addCards(EpisodeCardsDTO episodeCardsDTO);
    public void updateCards(EpisodeCardsDTO episodeCardsDTO);
    public EpisodeCardsDTO existUpdateData(Long id, EpisodeCardsDTO episodeCardsDTO);
}

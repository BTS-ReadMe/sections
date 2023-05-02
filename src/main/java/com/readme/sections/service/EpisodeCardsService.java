package com.readme.sections.service;

import com.readme.sections.dto.EpisodeCardsDTO;
import com.readme.sections.model.EpisodeCards;

public interface EpisodeCardsService {
    public EpisodeCards getCards(Long id);
    public void addCards(EpisodeCardsDTO episodeCardsDTO);
    public void updateCards(EpisodeCardsDTO episodeCardsDTO);
    public EpisodeCardsDTO existUpdateData(Long id, EpisodeCardsDTO episodeCardsDTO);
    public void deleteCards(Long id);
}

package com.readme.sections.service;

import com.readme.sections.dto.EpisodeCardsEntityDTO;
import com.readme.sections.dto.EpisodeCardsPaginationDTO;

public interface EpisodeCardsService {
    public EpisodeCardsPaginationDTO getCards(Long novelId, Integer pagination);
    public void addCards(EpisodeCardsEntityDTO episodeCardsEntityDTO);
    public void updateCards(EpisodeCardsEntityDTO episodeCardsEntityDTO);
    public EpisodeCardsEntityDTO existUpdateData(Long id, EpisodeCardsEntityDTO episodeCardsEntityDTO);
    public void deleteCards(Long id);
}

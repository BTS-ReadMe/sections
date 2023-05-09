package com.readme.sections.service;

import com.readme.sections.dto.EpisodeCardsDTO;

public interface EpisodeCardsService {
    public EpisodeCardsDTO getCards(Long novelId, Integer pagination);
    public void addCards(EpisodeCardsDTO episodeCardsDTO);
    public void updateCards(EpisodeCardsDTO episodeCardsDTO);
    public EpisodeCardsDTO existUpdateData(Long id, EpisodeCardsDTO episodeCardsDTO);
    public void deleteCards(Long id);
}

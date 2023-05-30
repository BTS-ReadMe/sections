package com.readme.sections.service;

import com.readme.sections.dto.EpisodeCardsEntityDTO;
import com.readme.sections.dto.EpisodeCardsPaginationDTO;
import com.readme.sections.dto.EpisodeDTO;
import com.readme.sections.requestObject.RequestDeleteEpisode;

public interface EpisodeCardsService {
    public EpisodeCardsPaginationDTO getCards(Long novelId, Integer pagination, String sort);
    public void addEpisode(EpisodeDTO episodeDTO);
    public void addCards(EpisodeCardsEntityDTO episodeCardsEntityDTO);
    public void updateEpisode(EpisodeDTO episodeDTO);
    public void updateCards(EpisodeCardsEntityDTO episodeCardsEntityDTO);
    public EpisodeCardsEntityDTO existUpdateData(Long id, EpisodeCardsEntityDTO episodeCardsEntityDTO);
    public void deleteEpisode(RequestDeleteEpisode requestDeleteEpisode);
    public void deleteCards(Long id);
}

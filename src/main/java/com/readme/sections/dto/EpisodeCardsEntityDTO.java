package com.readme.sections.dto;

import com.readme.sections.model.EpisodeCards;
import com.readme.sections.model.EpisodeCards.Episode;
import com.readme.sections.requestObject.RequestEpisodeCards;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EpisodeCardsEntityDTO {
    private Long novelId;
    private List<Episode> episodes;

    public EpisodeCardsEntityDTO(RequestEpisodeCards requestEpisodeCards) {
        this.novelId = requestEpisodeCards.getNovelId();
        this.episodes = requestEpisodeCards.getEpisodes();
    }

    public EpisodeCardsEntityDTO(EpisodeCards episodeCards, EpisodeCardsEntityDTO episodeCardsEntityDTO) {
        this.novelId = episodeCards.getNovelId();
         this.episodes = episodeCardsEntityDTO.getEpisodes() != null ? episodeCardsEntityDTO.getEpisodes() : episodeCards.getEpisodes();
    }
}

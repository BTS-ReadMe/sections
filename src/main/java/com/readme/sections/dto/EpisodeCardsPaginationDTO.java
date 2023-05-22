package com.readme.sections.dto;

import com.readme.sections.model.EpisodeCards;
import com.readme.sections.model.EpisodeCards.Episode;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class EpisodeCardsPaginationDTO {
    private Long novelId;
    private List<Episode> episodes;
    private long totalElements;
    private int totalPages;

    public EpisodeCardsPaginationDTO(EpisodeCards episodeCards, int pageSize) {
        this.novelId = episodeCards.getNovelId();
        this.episodes = episodeCards.getEpisodes();
        this.totalElements = episodeCards.getEpisodeCount();
        this.totalPages = (int) Math.ceil((double) episodeCards.getEpisodeCount() / pageSize);
    }
}

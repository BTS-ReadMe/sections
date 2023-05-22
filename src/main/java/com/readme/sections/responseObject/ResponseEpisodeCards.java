package com.readme.sections.responseObject;

import com.readme.sections.dto.EpisodeCardsPaginationDTO;

import com.readme.sections.dto.EpisodeCardsPaginationDTO.ViewEpisode;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseEpisodeCards {
    private long novelId;
    private List<ViewEpisode> episodes;
    private long totalElements;
    private int totalPages;

    public ResponseEpisodeCards(EpisodeCardsPaginationDTO episodeCardsPaginationDTO) {
        this.novelId = episodeCardsPaginationDTO.getNovelId();
        this.episodes = episodeCardsPaginationDTO.getEpisodes();
        this.totalElements = episodeCardsPaginationDTO.getTotalElements();
        this.totalPages = episodeCardsPaginationDTO.getTotalPages();
    }
}

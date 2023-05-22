package com.readme.sections.dto;

import com.readme.sections.model.EpisodeCards;
import com.readme.sections.service.EpisodeCardsServiceImpl;
import com.readme.sections.service.NovelCardsServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EpisodeCardsPaginationDTO {

    private long novelId;
    private List<ViewEpisode> episodes;
    private long totalElements;
    private int totalPages;

    @Getter
    @NoArgsConstructor
    public static class ViewEpisode {

        private long id;
        private String name;
        private boolean free;
        private String registrationDate;
        private double starRating;
        private boolean isNew;

        public ViewEpisode(EpisodeCards.Episode episode) {
            this.id = episode.getId();
            this.name = episode.getName();
            this.free = episode.getFree();
            this.registrationDate = NovelCardsServiceImpl.getUtcToKoreanTime(
                episode.getRegistrationDate());
            this.starRating = Math.round(episode.getStarRating() * 100.0) / 100.0;
            this.isNew = EpisodeCardsServiceImpl.checkIsNew(episode.getRegistrationDate());
        }
    }

    public EpisodeCardsPaginationDTO(EpisodeCards episodeCards, int pageSize) {
        this.novelId = episodeCards.getNovelId();
        this.episodes = episodeCards.getEpisodes().stream().map(episode -> new ViewEpisode(episode))
            .collect(Collectors.toList());
        this.totalElements = episodeCards.getEpisodeCount();
        this.totalPages = (int) Math.ceil((double) episodeCards.getEpisodeCount() / pageSize);
    }
}

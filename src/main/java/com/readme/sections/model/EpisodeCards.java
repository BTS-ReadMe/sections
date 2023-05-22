package com.readme.sections.model;

import com.readme.sections.dto.EpisodeCardsDTO;
import com.readme.sections.service.EpisodeCardsServiceImpl;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "episode_cards")
@Getter
@NoArgsConstructor
public class EpisodeCards {
    @Id
    private Long novelId;
    private List<Episode> episodes;
    private Long episodeCount;
    @Getter
    @NoArgsConstructor
    public static class Episode {
        private Long id;
        private String name;
        private Boolean free;
        private Date registrationDate;
        private Float starRating;
        private Boolean isNew;

        public Episode(Episode episode) {
            this.id = episode.getId();
            this.name = episode.getName();
            this.free = episode.getFree();
            this.registrationDate = episode.getRegistrationDate();
            this.starRating = episode.getStarRating();
            this.isNew = EpisodeCardsServiceImpl.checkIsNew(episode.getRegistrationDate());
        }
    }

    public EpisodeCards(EpisodeCardsDTO episodeCardsDTO) {
        this.novelId = episodeCardsDTO.getNovelId();
        this.episodes = episodeCardsDTO.getEpisodes();
    }
}

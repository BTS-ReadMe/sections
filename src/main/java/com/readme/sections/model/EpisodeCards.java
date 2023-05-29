package com.readme.sections.model;

import com.readme.sections.dto.EpisodeCardsEntityDTO;
import com.readme.sections.dto.EpisodeDTO;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "episode_cards")
@Getter
@NoArgsConstructor
@ToString
public class EpisodeCards {
    @Id
    private String novelId;
    private List<Episode> episodes;
    private Long episodeCount;
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Episode {
        private Long id;
        private String title;
        private Boolean free;
        private Date registrationDate;
        private String status;
        private Double starRating;
        private Boolean isNew;

        public Episode(EpisodeDTO episodeDTO) {
            this.id = episodeDTO.getEpisodeId();
            this.title = episodeDTO.getTitle();
            this.free = episodeDTO.isFree();
            this.registrationDate = episodeDTO.getRegistration();
            this.status = episodeDTO.getStatus();
            this.starRating = 0.0;
        }
    }

    public EpisodeCards(EpisodeCardsEntityDTO episodeCardsEntityDTO) {
        this.novelId = String.valueOf(episodeCardsEntityDTO.getNovelId());
        this.episodes = episodeCardsEntityDTO.getEpisodes();
    }
}

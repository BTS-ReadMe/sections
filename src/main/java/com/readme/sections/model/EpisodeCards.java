package com.readme.sections.model;

import com.readme.sections.dto.EpisodeCardsEntityDTO;
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
    private String novelId;
    private List<Episode> episodes;
    private Long episodeCount;
    @Getter
    @NoArgsConstructor
    public static class Episode {
        private Long id;
        private String title;
        private Boolean free;
        private Date registrationDate;
        private Float starRating;
        private Boolean isNew;
    }

    public EpisodeCards(EpisodeCardsEntityDTO episodeCardsEntityDTO) {
        this.novelId = String.valueOf(episodeCardsEntityDTO.getNovelId());
        this.episodes = episodeCardsEntityDTO.getEpisodes();
    }
}

package com.readme.sections.model;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "episode_cards")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EpisodeCards {
    @Id
    Long novelId;
    List<Episode> episodes;
    Long episodeCount;
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Episode {
        Long id;
        String name;
        Boolean free;
        Date registrationDate;
        Float starRating;
        Boolean isNew;
    }
}

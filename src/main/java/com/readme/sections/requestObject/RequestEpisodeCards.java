package com.readme.sections.requestObject;

import com.readme.sections.model.EpisodeCards;
import com.readme.sections.model.EpisodeCards.Episode;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestEpisodeCards {
    Long novelId;
    List<EpisodeCards.Episode> episodes;
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class Episode {
        Long id;
        String name;
        Boolean free;
        Date registrationDate;
        Float starRating;
    }
}

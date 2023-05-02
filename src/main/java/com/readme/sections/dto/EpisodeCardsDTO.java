package com.readme.sections.dto;

import com.readme.sections.model.EpisodeCards.Episode;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EpisodeCardsDTO {
    Long novelId;
    List<Episode> episodes;
}

package com.readme.sections.requestObject;

import com.readme.sections.model.EpisodeCards.Episode;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestEpisodeCards {
    Long novelId;
    List<Episode> episodes;
}

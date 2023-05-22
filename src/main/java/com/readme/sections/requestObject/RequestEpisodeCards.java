package com.readme.sections.requestObject;

import com.readme.sections.model.EpisodeCards.Episode;
import java.util.List;
import lombok.Getter;

@Getter
public class RequestEpisodeCards {
    private long novelId;
    private List<Episode> episodes;
}

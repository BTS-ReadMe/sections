package com.readme.sections.dto;

import com.readme.sections.requestObject.RequestEpisode;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EpisodeDTO {
    private long episodeId;
    private long novelId;
    private String title;
    private boolean free;
    private Date registration;
    private String status;

    public EpisodeDTO(RequestEpisode requestEpisode) {
        this.episodeId = requestEpisode.getEpisodeId();
        this.novelId = requestEpisode.getNovelId();
        this.title = requestEpisode.getTitle();
        this.free = requestEpisode.isFree();
        this.registration = requestEpisode.getRegistration();
        this.status = requestEpisode.getStatus();
    }
}

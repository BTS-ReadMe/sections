package com.readme.sections.requestObject;

import java.util.Date;
import lombok.Getter;

@Getter
public class RequestKafkaEpisode {
    private long episodeId;
    private long novelId;
    private String title;
    private boolean free;
    private Date registration;
    private String status;
}

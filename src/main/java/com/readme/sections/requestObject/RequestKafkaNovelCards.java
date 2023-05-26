package com.readme.sections.requestObject;

import java.util.Date;
import java.util.List;
import lombok.Getter;

@Getter
public class RequestKafkaNovelCards {
    private Long novelId;
    private String title;
    private String description;
    private String author;
    private String authorComment;
    private String genre;
    private Integer grade;
    private String thumbnail;
    private Date startDate;
    private Long views;
    private String serializationStatus;
    private List<String> tags;
    private Long scheduleId;
    private Double starRating;
    private List<String> serializationDay;
    private Long episodeCount;
}

package com.readme.sections.requestObject;

import com.readme.sections.model.NovelCards.Tag;
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
    private List<Tag> tags;
    private Long scheduleId;
    private Double starRating;
    private List<String> serializationDay;
    private Long episodeCount;
}

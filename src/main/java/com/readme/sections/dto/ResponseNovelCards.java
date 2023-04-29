package com.readme.sections.dto;

import com.readme.sections.model.NovelCards;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ResponseNovelCards {
    Long novelId;
    String title;
    String description;
    String author;
    String genre;
    Integer grade;
    String thumbnail;
    Date startDate;
    String serializationDays;
    Long views;
    String serializationStatus;
    List<NovelCards.Tag> tags;
    Long scheduleId;
    Float startRating;
    @Getter
    @Builder
    @ToString
    public static class Tag {
        Long id;
        String name;
    }
}

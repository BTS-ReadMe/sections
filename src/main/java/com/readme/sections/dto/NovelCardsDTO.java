package com.readme.sections.dto;

import java.util.Date;
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
public class NovelCardsDTO {
    Long novelId;
    String title;
    String description;
    String author;
    String genre;
    Integer grade;
    String thumbnail;
    Date startDate;
    Integer serializationDays;
    Long views;
    String serializationStatus;
    List<Tag> tags;
    Long scheduleId;
    Float starRating;
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Tag {
        Long id;
        String name;
    }
}

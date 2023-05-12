package com.readme.sections.dto;

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
public class NovelCardsViewDTO {
    Long novelId;
    String title;
    String description;
    String author;
    String genre;
    Integer grade;
    String thumbnail;
    String startDate;
    Long views;
    String serializationStatus;
    List<Tag> tags;
    Long scheduleId;
    Double starRating;
    String serializationDays;
    Boolean newChecking;
    Long episodeCount;
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

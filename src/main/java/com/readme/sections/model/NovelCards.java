package com.readme.sections.model;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "novel_cards")
@Getter
@Builder
@ToString
public class NovelCards {
    @Id
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
    Float startRating;
    @Getter
    @Builder
    @ToString
    public static class Tag {
        Long id;
        String name;
    }
}
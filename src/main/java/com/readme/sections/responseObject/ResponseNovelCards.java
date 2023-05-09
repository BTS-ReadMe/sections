package com.readme.sections.responseObject;

import com.readme.sections.dto.NovelCardsDTO.Tag;
import java.util.Date;
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
public class ResponseNovelCards {
    Long novelId;
    String title;
    String description;
    String author;
    String genre;
    Integer grade;
    String thumbnail;
    Date startDate;
    Long views;
    String serializationStatus;
    List<Tag> tags;
    Long scheduleId;
    Float starRating;
    Boolean monday;
    Boolean tuesday;
    Boolean wednesday;
    Boolean thursday;
    Boolean friday;
    Boolean saturday;
    Boolean sunday;
    Boolean isNew;
    Long episodeCount;
}

package com.readme.sections.vo;

import com.readme.sections.dto.ResponseNovelCards.Tag;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RequestNovelCards {
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
}

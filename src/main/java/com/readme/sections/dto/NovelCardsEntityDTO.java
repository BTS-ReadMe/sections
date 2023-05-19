package com.readme.sections.dto;

import com.readme.sections.model.NovelCards.Tag;
import com.readme.sections.requestObject.RequestNovelCards;
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
public class NovelCardsEntityDTO {
    Long novelId;
    String title;
    String description;
    String author;
    String authorComment;
    String genre;
    Integer grade;
    String thumbnail;
    Date startDate;
    Long views;
    String serializationStatus;
    List<Tag> tags;
    Long scheduleId;
    Double starRating;
    Boolean monday;
    Boolean tuesday;
    Boolean wednesday;
    Boolean thursday;
    Boolean friday;
    Boolean saturday;
    Boolean sunday;
    Long episodeCount;

    public NovelCardsEntityDTO (RequestNovelCards requestNovelCards) {
        this.novelId = requestNovelCards.getNovelId();
        this.title = requestNovelCards.getTitle();
        this.description = requestNovelCards.getDescription();
        this.author = requestNovelCards.getAuthor();
        this.authorComment = requestNovelCards.getAuthorComment();
        this.genre = requestNovelCards.getGenre();
        this.grade = requestNovelCards.getGrade();
        this.thumbnail = requestNovelCards.getThumbnail();
        this.startDate = requestNovelCards.getStartDate();
        this.views = requestNovelCards.getViews();
        this.serializationStatus = requestNovelCards.getSerializationStatus();
        this.tags = requestNovelCards.getTags();
        this.scheduleId = requestNovelCards.getScheduleId();
        this.starRating = requestNovelCards.getStarRating();
        this.monday = requestNovelCards.getMonday();
        this.tuesday = requestNovelCards.getTuesday();
        this.wednesday = requestNovelCards.getWednesday();
        this.thursday = requestNovelCards.getThursday();
        this.friday = requestNovelCards.getFriday();
        this.saturday = requestNovelCards.getSaturday();
        this.sunday= requestNovelCards.getSunday();
        this.episodeCount = requestNovelCards.getEpisodeCount();
    }
}

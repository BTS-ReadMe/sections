package com.readme.sections.dto;

import com.readme.sections.model.NovelCards;
import com.readme.sections.model.NovelCards.Tag;
import com.readme.sections.requestObject.RequestNovelCards;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

    public NovelCardsEntityDTO(NovelCards novelCards, NovelCardsEntityDTO novelCardsEntityDTO) {
        this.novelId = novelCards.getNovelId();
        this.title = novelCardsEntityDTO.getTitle() != null ? novelCardsEntityDTO.getTitle()
            : novelCards.getTitle();
        this.description = novelCardsEntityDTO.getDescription() != null ? novelCardsEntityDTO.getDescription()
            : novelCards.getDescription();
        this.author = novelCardsEntityDTO.getAuthor() != null ? novelCardsEntityDTO.getAuthor()
            : novelCards.getAuthor();
        this.authorComment = novelCardsEntityDTO.getAuthorComment() != null ? novelCardsEntityDTO.getAuthorComment()
            : novelCards.getAuthorComment();
        this.genre = novelCardsEntityDTO.getGenre() != null ? novelCardsEntityDTO.getGenre()
            : novelCards.getGenre();
        this.grade = novelCardsEntityDTO.getGrade() != null ? novelCardsEntityDTO.getGrade()
            : novelCards.getGrade();
        this.thumbnail = novelCardsEntityDTO.getThumbnail() != null ? novelCardsEntityDTO.getThumbnail()
            : novelCards.getThumbnail();
        this.startDate = novelCardsEntityDTO.getStartDate() != null ? novelCardsEntityDTO.getStartDate()
            : novelCards.getStartDate();
        this.views = novelCardsEntityDTO.getViews() != null ? novelCardsEntityDTO.getViews()
            : novelCards.getViews();
        this.serializationStatus = novelCardsEntityDTO.getSerializationStatus() != null
            ? novelCardsEntityDTO.getSerializationStatus()
            : novelCards.getSerializationStatus();
        this.tags = novelCardsEntityDTO.getTags() != null ? novelCardsEntityDTO.getTags()
            : novelCards.getTags();
        this.scheduleId = novelCardsEntityDTO.getScheduleId() != null ? novelCardsEntityDTO.getScheduleId()
            : novelCards.getScheduleId();
        this.starRating = novelCardsEntityDTO.getStarRating() != null ? novelCardsEntityDTO.getStarRating()
            : novelCards.getStarRating();
        this.monday = novelCardsEntityDTO.getMonday() != null ? novelCardsEntityDTO.getMonday()
            : novelCards.getMonday();
        this.tuesday = novelCardsEntityDTO.getTuesday() != null ? novelCardsEntityDTO.getTuesday()
            : novelCards.getTuesday();
        this.wednesday = novelCardsEntityDTO.getWednesday() != null ? novelCardsEntityDTO.getWednesday()
            : novelCards.getWednesday();
        this.thursday = novelCardsEntityDTO.getThursday() != null ? novelCardsEntityDTO.getThursday()
            : novelCards.getThursday();
        this.friday = novelCardsEntityDTO.getFriday() != null ? novelCardsEntityDTO.getFriday()
            : novelCards.getFriday();
        this.saturday = novelCardsEntityDTO.getSaturday() != null ? novelCardsEntityDTO.getSaturday()
            : novelCards.getSaturday();
        this.sunday = novelCardsEntityDTO.getSunday() != null ? novelCardsEntityDTO.getSunday()
            : novelCards.getSunday();
        this.episodeCount = novelCardsEntityDTO.getEpisodeCount() != null
            ? novelCardsEntityDTO.getEpisodeCount()
            : novelCards.getEpisodeCount();
    }
}

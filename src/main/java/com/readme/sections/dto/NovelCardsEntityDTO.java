package com.readme.sections.dto;

import com.readme.sections.enums.SerializationDays;
import com.readme.sections.model.NovelCards;
import com.readme.sections.model.NovelCards.Tag;
import com.readme.sections.requestObject.RequestNovelCards;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NovelCardsEntityDTO {
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
    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;
    private Boolean sunday;
    private Long episodeCount;

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
        this.views = requestNovelCards.getViews() != null ? requestNovelCards.getViews() : 0L;
        this.serializationStatus = requestNovelCards.getSerializationStatus();
        this.tags = requestNovelCards.getTags().stream()
            .map(tag -> new Tag(tag))
            .collect(Collectors.toList());
        this.scheduleId = requestNovelCards.getScheduleId() != null ? requestNovelCards.getScheduleId() : null;
        this.starRating = requestNovelCards.getStarRating() != null ? requestNovelCards.getStarRating() : 0.0;
        this.monday = requestNovelCards.getSerializationDay().contains(SerializationDays.월.getShortDay());
        this.tuesday = requestNovelCards.getSerializationDay().contains(SerializationDays.화.getShortDay());
        this.wednesday = requestNovelCards.getSerializationDay().contains(SerializationDays.수.getShortDay());
        this.thursday = requestNovelCards.getSerializationDay().contains(SerializationDays.목.getShortDay());
        this.friday = requestNovelCards.getSerializationDay().contains(SerializationDays.금.getShortDay());
        this.saturday = requestNovelCards.getSerializationDay().contains(SerializationDays.토.getShortDay());
        this.sunday= requestNovelCards.getSerializationDay().contains(SerializationDays.일.getShortDay());
        this.episodeCount = requestNovelCards.getEpisodeCount() != null ? requestNovelCards.getEpisodeCount() : 0L;
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
        this.tags = novelCardsEntityDTO.getTags() != null ? novelCardsEntityDTO.getTags().stream()
            .map(tag -> new Tag(tag.getName()))
            .collect(Collectors.toList())
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

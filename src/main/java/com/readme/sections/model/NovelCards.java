package com.readme.sections.model;

import com.readme.sections.dto.NovelCardsEntityDTO;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "novel_cards")
@Getter
@NoArgsConstructor
@CompoundIndex(def = "{'genre': 1, 'serializationStatus': 1}")
public class NovelCards {
    @Id
    private Long novelId;
    @Indexed
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

    @Getter
    public static class Tag {
        private Long id;
        private String name;
    }

    public double getStarRating() {
        return Math.round(this.starRating * 100.0) / 100.0;
    }

    public NovelCards(NovelCardsEntityDTO novelCardsEntityDTO) {
        this.novelId = novelCardsEntityDTO.getNovelId();
        this.title = novelCardsEntityDTO.getTitle();
        this.description = novelCardsEntityDTO.getDescription();
        this.author = novelCardsEntityDTO.getAuthor();
        this.authorComment = novelCardsEntityDTO.getAuthorComment();
        this.genre = novelCardsEntityDTO.getGenre();
        this.grade = novelCardsEntityDTO.getGrade();
        this.thumbnail = novelCardsEntityDTO.getThumbnail();
        this.startDate = novelCardsEntityDTO.getStartDate();
        this.views = novelCardsEntityDTO.getViews();
        this.serializationStatus = novelCardsEntityDTO.getSerializationStatus();
        this.tags = novelCardsEntityDTO.getTags();
        this.scheduleId = novelCardsEntityDTO.getScheduleId();
        this.starRating = novelCardsEntityDTO.getStarRating();
        this.monday = novelCardsEntityDTO.getMonday();
        this.tuesday = novelCardsEntityDTO.getTuesday();
        this.wednesday = novelCardsEntityDTO.getWednesday();
        this.thursday = novelCardsEntityDTO.getThursday();
        this.friday = novelCardsEntityDTO.getFriday();
        this.saturday = novelCardsEntityDTO.getSaturday();
        this.sunday= novelCardsEntityDTO.getSunday();
        this.episodeCount = novelCardsEntityDTO.getEpisodeCount();
    }
}
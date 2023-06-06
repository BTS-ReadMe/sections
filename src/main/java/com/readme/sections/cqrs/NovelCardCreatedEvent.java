package com.readme.sections.cqrs;

import com.readme.sections.model.NovelCards.Tag;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NovelCardCreatedEvent {
    private String novelId;
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

    public NovelCardCreatedEvent(CreateNovelCardCommand createNovelCardCommand) {
        this.novelId = createNovelCardCommand.getNovelId();
        this.title = createNovelCardCommand.getTitle();
        this.description = createNovelCardCommand.getDescription();
        this.author = createNovelCardCommand.getAuthor();
        this.authorComment = createNovelCardCommand.getAuthorComment();
        this.genre = createNovelCardCommand.getGenre();
        this.grade = createNovelCardCommand.getGrade();
        this.thumbnail = createNovelCardCommand.getThumbnail();
        this.startDate = createNovelCardCommand.getStartDate();
        this.views = createNovelCardCommand.getViews();
        this.serializationStatus = createNovelCardCommand.getSerializationStatus();
        this.tags = createNovelCardCommand.getTags();
        this.scheduleId = createNovelCardCommand.getScheduleId();
        this.starRating = createNovelCardCommand.getStarRating();
        this.monday = createNovelCardCommand.getMonday();
        this.tuesday = createNovelCardCommand.getTuesday();
        this.wednesday = createNovelCardCommand.getWednesday();
        this.thursday = createNovelCardCommand.getThursday();
        this.friday = createNovelCardCommand.getFriday();
        this.saturday = createNovelCardCommand.getSaturday();
        this.sunday = createNovelCardCommand.getSunday();
        this.episodeCount = createNovelCardCommand.getEpisodeCount();
    }
}

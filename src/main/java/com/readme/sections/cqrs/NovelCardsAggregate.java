package com.readme.sections.cqrs;

import com.readme.sections.model.NovelCards.Tag;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Getter
public class NovelCardsAggregate {
    @AggregateIdentifier
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

    @CommandHandler
    public NovelCardsAggregate(CreateNovelCardCommand cmd) {
        AggregateLifecycle.apply(new NovelCardCreatedEvent(cmd));
    }

    @EventSourcingHandler
    public void on(NovelCardCreatedEvent evt) {
        this.novelId = evt.getNovelId();
        this.title = evt.getTitle();
        this.description = evt.getDescription();
        this.author = evt.getAuthor();
        this.authorComment = evt.getAuthorComment();
        this.genre = evt.getGenre();
        this.grade = evt.getGrade();
        this.thumbnail = evt.getThumbnail();
        this.startDate = evt.getStartDate();
        this.views = evt.getViews();
        this.serializationStatus = evt.getSerializationStatus();
        this.tags = evt.getTags();
        this.scheduleId = evt.getScheduleId();
        this.starRating = evt.getStarRating();
        this.monday = evt.getMonday();
        this.tuesday = evt.getTuesday();
        this.wednesday = evt.getWednesday();
        this.thursday = evt.getThursday();
        this.friday = evt.getFriday();
        this.saturday = evt.getSaturday();
        this.sunday = evt.getSunday();
        this.episodeCount = evt.getEpisodeCount();
    }
}

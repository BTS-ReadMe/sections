package com.readme.sections.dto;

import com.readme.sections.model.NovelCards;
import com.readme.sections.model.NovelCards.Tag;
import com.readme.sections.service.NovelCardsServiceImpl;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NovelCardsViewDTO {
    private long novelId;
    private String title;
    private String description;
    private String author;
    private String authorComment;
    private String genre;
    private int grade;
    private String thumbnail;
    private String startDate;
    private long views;
    private String serializationStatus;
    private List<Tag> tags;
    private Long scheduleId;
    private double starRating;
    private String serializationDays;
    private boolean newChecking;
    private long episodeCount;

    public NovelCardsViewDTO(NovelCards novelCards) {
        this.novelId = novelCards.getNovelId();
        this.title = novelCards.getTitle();
        this.description = novelCards.getDescription();
        this.author = novelCards.getAuthor();
        this.authorComment = novelCards.getAuthorComment();
        this.genre = novelCards.getGenre();
        this.grade = novelCards.getGrade();
        this.thumbnail = novelCards.getThumbnail();
        this.startDate = NovelCardsServiceImpl.getUtcToKoreanTime(novelCards.getStartDate());
        this.views = novelCards.getViews();
        this.serializationStatus = novelCards.getSerializationStatus();
        this.tags = novelCards.getTags();
        this.scheduleId = novelCards.getScheduleId();
        this.starRating = novelCards.getStarRating();
        this.serializationDays = NovelCardsServiceImpl.getSerializationDays(novelCards);
        this.newChecking = NovelCardsServiceImpl.checkNewNovel(novelCards.getStartDate());
        this.episodeCount = novelCards.getEpisodeCount();
    }
}

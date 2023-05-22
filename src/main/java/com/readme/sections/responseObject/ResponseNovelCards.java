package com.readme.sections.responseObject;

import com.readme.sections.dto.NovelCardsViewDTO;
import com.readme.sections.model.NovelCards.Tag;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseNovelCards {
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

    public ResponseNovelCards(NovelCardsViewDTO novelCardsViewDTO) {
        this.novelId = novelCardsViewDTO.getNovelId();
        this.title = novelCardsViewDTO.getTitle();
        this.description = novelCardsViewDTO.getDescription();
        this.author = novelCardsViewDTO.getAuthor();
        this.authorComment = novelCardsViewDTO.getAuthorComment();
        this.genre = novelCardsViewDTO.getGenre();
        this.grade = novelCardsViewDTO.getGrade();
        this.thumbnail = novelCardsViewDTO.getThumbnail();
        this.startDate = novelCardsViewDTO.getStartDate();
        this.views = novelCardsViewDTO.getViews();
        this.serializationStatus = novelCardsViewDTO.getSerializationStatus();
        this.tags = novelCardsViewDTO.getTags();
        this.scheduleId = novelCardsViewDTO.getScheduleId();
        this.starRating = novelCardsViewDTO.getStarRating();
        this.serializationDays = novelCardsViewDTO.getSerializationDays();
        this.newChecking = novelCardsViewDTO.isNewChecking();
        this.episodeCount = novelCardsViewDTO.getEpisodeCount();
    }
}

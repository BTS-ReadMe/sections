package com.readme.sections.dto;

import com.readme.sections.model.NovelCards;
import com.readme.sections.model.NovelCards.Tag;
import com.readme.sections.service.NovelCardsServiceImpl;
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
public class NovelCardsViewDTO {
    Long novelId;
    String title;
    String description;
    String author;
    String authorComment;
    String genre;
    Integer grade;
    String thumbnail;
    String startDate;
    Long views;
    String serializationStatus;
    List<Tag> tags;
    Long scheduleId;
    Double starRating;
    String serializationDays;
    Boolean newChecking;
    Long episodeCount;

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
        this.serializationDays = novelCards.getSerializationStatus();
        this.newChecking = NovelCardsServiceImpl.checkNewNovel(novelCards.getStartDate());
        this.episodeCount = novelCards.getEpisodeCount();
    }
}

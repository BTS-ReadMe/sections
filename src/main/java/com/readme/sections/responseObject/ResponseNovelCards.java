package com.readme.sections.responseObject;

import com.readme.sections.dto.NovelCardsViewDTO;
import com.readme.sections.model.NovelCards.Tag;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseNovelCards {
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
        this.newChecking = novelCardsViewDTO.getNewChecking();
        this.episodeCount = novelCardsViewDTO.getEpisodeCount();
    }
}

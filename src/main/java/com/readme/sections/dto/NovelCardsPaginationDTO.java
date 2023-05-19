package com.readme.sections.dto;

import com.readme.sections.dataAccessLayer.NovelCardsDataAccessLayer;
import com.readme.sections.model.NovelCards;
import com.readme.sections.service.NovelCardsServiceImpl;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NovelCardsPaginationDTO {

    List<NovelCardsData> novelCardsData;
    private long totalElements;
    private int totalPages;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class NovelCardsData {

        private Long novelId;
        private String title;
        private String description;
        private String author;
        private String genre;
        private int grade;
        private String thumbnail;
        private String serializationStatus;
        private double starRating;
        private boolean newChecking;
        private long episodeCount;

        public NovelCardsData(Long novelId, String title, String description, String author,
            String genre, Integer grade, String thumbnail, String serializationStatus,
            double starRating, Long episodeCount, Date startDate) {
            this.novelId = novelId;
            this.title = title;
            this.description = description;
            this.author = author;
            this.genre = genre;
            this.grade = grade;
            this.thumbnail = thumbnail;
            this.serializationStatus = serializationStatus;
            this.starRating = starRating;
            this.newChecking = NovelCardsServiceImpl.checkNewNovel(startDate);
            this.episodeCount = episodeCount;
        }
    }

    public NovelCardsPaginationDTO(Page<NovelCards> novelCardsPage) {
        this.novelCardsData = novelCardsPage.stream()
            .map(novelCards -> new NovelCardsData(novelCards.getNovelId(),
                novelCards.getTitle(), novelCards.getDescription(), novelCards.getAuthor(),
                novelCards.getGenre(), novelCards.getGrade(), novelCards.getThumbnail(),
                novelCards.getSerializationStatus(), novelCards.getStarRating(),
                novelCards.getEpisodeCount(), novelCards.getStartDate()))
            .collect(Collectors.toList());
        this.totalElements = novelCardsPage.getTotalElements();
        this.totalPages = novelCardsPage.getTotalPages();
    }

}

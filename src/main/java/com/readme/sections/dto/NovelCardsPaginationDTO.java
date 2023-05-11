package com.readme.sections.dto;

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
        private boolean isNew;
        private long episodeCount;
    }

}

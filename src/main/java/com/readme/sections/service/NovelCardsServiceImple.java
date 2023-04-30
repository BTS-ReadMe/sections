package com.readme.sections.service;

import com.readme.sections.dto.ResponseNovelCards;
import com.readme.sections.model.NovelCards;
import com.readme.sections.repository.NovelCardsRepository;
import com.readme.sections.vo.RequestNovelCards;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelCardsServiceImple implements NovelCardsService {

    private final NovelCardsRepository novelCardsRepository;

    public void addCards(RequestNovelCards requestNovelCards) {
        novelCardsRepository.insert(NovelCards.builder()
            .novelId(requestNovelCards.getNovelId())
            .title(requestNovelCards.getTitle())
            .author(requestNovelCards.getAuthor())
            .grade(requestNovelCards.getGrade())
            .genre(requestNovelCards.getGenre())
            .tags(requestNovelCards.getTags())
            .serializationDays(requestNovelCards.getSerializationDays())
            .thumbnail(requestNovelCards.getThumbnail())
            .views(requestNovelCards.getViews())
            .description(requestNovelCards.getDescription())
            .scheduleId(requestNovelCards.getScheduleId())
            .startDate(requestNovelCards.getStartDate())
            .starRating(requestNovelCards.getStarRating())
            .build());
    }
}

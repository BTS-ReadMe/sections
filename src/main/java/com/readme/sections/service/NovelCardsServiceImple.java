package com.readme.sections.service;

import com.readme.sections.dto.ResponseNovelCards;
import com.readme.sections.dto.ResponseNovelCards.Tag;
import com.readme.sections.model.NovelCards;
import com.readme.sections.repository.NovelCardsRepository;
import com.readme.sections.vo.RequestNovelCards;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelCardsServiceImple implements NovelCardsService {

    private final NovelCardsRepository novelCardsRepository;

    public ResponseNovelCards getCards(Long id) {
        NovelCards novelCards = novelCardsRepository.findById(id).get();
        return ResponseNovelCards.builder()
            .novelId(novelCards.getNovelId())
            .title(novelCards.getTitle())
            .author(novelCards.getAuthor())
            .grade(novelCards.getGrade())
            .genre(novelCards.getGenre())
            .tags(novelCards.getTags().stream()
                .map(element -> Tag.builder()
                    .id(element.getId())
                    .name(element.getName())
                    .build()).collect(Collectors.toList()))
            .serializationDays(novelCards.getSerializationDays())
            .thumbnail(novelCards.getThumbnail())
            .views(novelCards.getViews())
            .serializationStatus(novelCards.getSerializationStatus())
            .description(novelCards.getDescription())
            .scheduleId(novelCards.getScheduleId())
            .startDate(novelCards.getStartDate())
            .starRating(novelCards.getStarRating())
            .build();
    }

    public void addCards(RequestNovelCards requestNovelCards) {
        novelCardsRepository.insert(NovelCards.builder()
            .novelId(requestNovelCards.getNovelId())
            .title(requestNovelCards.getTitle())
            .author(requestNovelCards.getAuthor())
            .grade(requestNovelCards.getGrade())
            .genre(requestNovelCards.getGenre())
            .tags(requestNovelCards.getTags().stream()
                .map(element -> Tag.builder()
                    .id(element.getId())
                    .name(element.getName())
                    .build()).collect(Collectors.toList()))
            .serializationDays(requestNovelCards.getSerializationDays())
            .thumbnail(requestNovelCards.getThumbnail())
            .views(requestNovelCards.getViews())
            .serializationStatus(requestNovelCards.getSerializationStatus())
            .description(requestNovelCards.getDescription())
            .scheduleId(requestNovelCards.getScheduleId())
            .startDate(requestNovelCards.getStartDate())
            .starRating(requestNovelCards.getStarRating())
            .build());
    }

    @Override
    public void updateCards(Long id, RequestNovelCards requestNovelCards) throws NotFoundException {
        novelCardsRepository.save(novelCardsRepository.findById(id)
            .map(novelToUpdate -> NovelCards.builder()
                .novelId(novelToUpdate.getNovelId())
                .title(requestNovelCards.getTitle() != null ? requestNovelCards.getTitle()
                    : novelToUpdate.getTitle())
                .description(
                    requestNovelCards.getDescription() != null ? requestNovelCards.getDescription()
                        : novelToUpdate.getDescription())
                .author(requestNovelCards.getAuthor() != null ? requestNovelCards.getAuthor()
                    : novelToUpdate.getAuthor())
                .genre(requestNovelCards.getGenre() != null ? requestNovelCards.getGenre()
                    : novelToUpdate.getGenre())
                .grade(requestNovelCards.getGrade() != null ? requestNovelCards.getGrade()
                    : novelToUpdate.getGrade())
                .thumbnail(
                    requestNovelCards.getThumbnail() != null ? requestNovelCards.getThumbnail()
                        : novelToUpdate.getThumbnail())
                .startDate(
                    requestNovelCards.getStartDate() != null ? requestNovelCards.getStartDate()
                        : novelToUpdate.getStartDate())
                .serializationDays(requestNovelCards.getSerializationDays() != null
                    ? requestNovelCards.getSerializationDays()
                    : novelToUpdate.getSerializationDays())
                .views(requestNovelCards.getViews() != null ? requestNovelCards.getViews()
                    : novelToUpdate.getViews())
                .serializationStatus(requestNovelCards.getSerializationStatus() != null
                    ? requestNovelCards.getSerializationStatus()
                    : novelToUpdate.getSerializationStatus())
                .tags(requestNovelCards.getTags() != null ? requestNovelCards.getTags()
                    : novelToUpdate.getTags())
                .scheduleId(
                    requestNovelCards.getScheduleId() != null ? requestNovelCards.getScheduleId()
                        : novelToUpdate.getScheduleId())
                .starRating(
                    requestNovelCards.getStarRating() != null ? requestNovelCards.getStarRating()
                        : novelToUpdate.getStarRating())
                .build())
            .orElseThrow(() -> new NotFoundException()));
    }

    @Override
    public void deleteCards(Long id) {
        novelCardsRepository.deleteById(id);
    }
}

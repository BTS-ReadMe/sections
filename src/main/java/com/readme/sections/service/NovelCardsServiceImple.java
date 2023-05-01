package com.readme.sections.service;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.dto.NovelCardsDTO.Tag;
import com.readme.sections.model.NovelCards;
import com.readme.sections.repository.NovelCardsRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelCardsServiceImple implements NovelCardsService {

    private final NovelCardsRepository novelCardsRepository;
    private final ModelMapper modelMapper;

    public NovelCardsDTO getCards(Long id) {
        NovelCards novelCards = novelCardsRepository.findById(id).get();
        return modelMapper.map(novelCards, NovelCardsDTO.class);
    }

    public void addCards(NovelCardsDTO novelCardsDTO) {
        novelCardsRepository.insert(NovelCards.builder()
            .novelId(novelCardsDTO.getNovelId())
            .title(novelCardsDTO.getTitle())
            .author(novelCardsDTO.getAuthor())
            .grade(novelCardsDTO.getGrade())
            .genre(novelCardsDTO.getGenre())
            .tags(novelCardsDTO.getTags().stream()
                .map(element -> Tag.builder()
                    .id(element.getId())
                    .name(element.getName())
                    .build()).collect(Collectors.toList()))
            .serializationDays(novelCardsDTO.getSerializationDays())
            .thumbnail(novelCardsDTO.getThumbnail())
            .views(novelCardsDTO.getViews())
            .serializationStatus(novelCardsDTO.getSerializationStatus())
            .description(novelCardsDTO.getDescription())
            .scheduleId(novelCardsDTO.getScheduleId())
            .startDate(novelCardsDTO.getStartDate())
            .starRating(novelCardsDTO.getStarRating())
            .build());
    }

    @Override
    public NovelCardsDTO existUpdateData(Long id, NovelCardsDTO novelCardsDTO) {
        NovelCards novelCards = novelCardsRepository.findById(id).get();
        return NovelCardsDTO.builder()
            .novelId(novelCards.getNovelId())
            .title(novelCardsDTO.getTitle() != null ? novelCardsDTO.getTitle()
                : novelCards.getTitle())
            .description(
                novelCardsDTO.getDescription() != null ? novelCardsDTO.getDescription()
                    : novelCards.getDescription())
            .author(novelCardsDTO.getAuthor() != null ? novelCardsDTO.getAuthor()
                : novelCards.getAuthor())
            .genre(novelCardsDTO.getGenre() != null ? novelCardsDTO.getGenre()
                : novelCards.getGenre())
            .grade(novelCardsDTO.getGrade() != null ? novelCardsDTO.getGrade()
                : novelCards.getGrade())
            .thumbnail(
                novelCardsDTO.getThumbnail() != null ? novelCardsDTO.getThumbnail()
                    : novelCards.getThumbnail())
            .startDate(
                novelCardsDTO.getStartDate() != null ? novelCardsDTO.getStartDate()
                    : novelCards.getStartDate())
            .serializationDays(novelCardsDTO.getSerializationDays() != null
                ? novelCardsDTO.getSerializationDays()
                : novelCards.getSerializationDays())
            .views(novelCardsDTO.getViews() != null ? novelCardsDTO.getViews()
                : novelCards.getViews())
            .serializationStatus(novelCardsDTO.getSerializationStatus() != null
                ? novelCardsDTO.getSerializationStatus()
                : novelCards.getSerializationStatus())
            .tags(novelCardsDTO.getTags() != null ? novelCardsDTO.getTags()
                : novelCards.getTags())
            .scheduleId(
                novelCardsDTO.getScheduleId() != null ? novelCardsDTO.getScheduleId()
                    : novelCards.getScheduleId())
            .starRating(
                novelCardsDTO.getStarRating() != null ? novelCardsDTO.getStarRating()
                    : novelCards.getStarRating())
            .build();
    }

    @Override
    public void updateCards(NovelCardsDTO novelCardsDTO){
        novelCardsRepository.save(modelMapper.map(novelCardsDTO, NovelCards.class));
    }

    @Override
    public void deleteCards(Long id) {
        novelCardsRepository.deleteById(id);
    }
}

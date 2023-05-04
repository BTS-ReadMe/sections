package com.readme.sections.service;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.dto.NovelCardsDTO.Tag;
import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO.NovelCardsData;
import com.readme.sections.model.NovelCards;
import com.readme.sections.repository.NovelCardsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelCardsServiceImpl implements NovelCardsService {

    private final NovelCardsRepository novelCardsRepository;
    private final ModelMapper modelMapper;

    public NovelCardsDTO getCards(Long id) {
        NovelCards novelCards = novelCardsRepository.findById(id).get();
        return modelMapper.map(novelCards, NovelCardsDTO.class);
    }

    @Override
    public NovelCardsPaginationDTO getAllCards(Pageable pageable) {
        Page<NovelCards> novelCardsList = novelCardsRepository.findAll(pageable);
        List<NovelCardsData> novelCardsData = novelCardsList.stream()
            .map(novel -> modelMapper.map(novel, NovelCardsData.class))
            .collect(Collectors.toList());
        return NovelCardsPaginationDTO.builder()
            .novelCardsData(novelCardsData)
            .size(novelCardsList.getSize())
            .page(novelCardsList.getNumber())
            .totalElements(novelCardsList.getTotalElements())
            .totalPages(novelCardsList.getTotalPages())
            .build();
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
            .thumbnail(novelCardsDTO.getThumbnail())
            .views(novelCardsDTO.getViews())
            .serializationStatus(novelCardsDTO.getSerializationStatus())
            .description(novelCardsDTO.getDescription())
            .scheduleId(novelCardsDTO.getScheduleId())
            .startDate(novelCardsDTO.getStartDate())
            .starRating(novelCardsDTO.getStarRating())
            .monday(novelCardsDTO.getMonday())
            .tuesday(novelCardsDTO.getTuesday())
            .wednesday(novelCardsDTO.getWednesday())
            .thursday(novelCardsDTO.getThursday())
            .friday(novelCardsDTO.getFriday())
            .saturday(novelCardsDTO.getSaturday())
            .sunday(novelCardsDTO.getSunday())
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
            .monday(
                novelCardsDTO.getMonday() != null ? novelCardsDTO.getMonday()
                    : novelCards.getMonday())
            .tuesday(
                novelCardsDTO.getTuesday() != null ? novelCardsDTO.getTuesday()
                    : novelCards.getTuesday())
            .wednesday(
                novelCardsDTO.getWednesday() != null ? novelCardsDTO.getWednesday()
                    : novelCards.getWednesday())
            .thursday(
                novelCardsDTO.getThursday() != null ? novelCardsDTO.getThursday()
                    : novelCards.getThursday())
            .friday(
                novelCardsDTO.getFriday() != null ? novelCardsDTO.getFriday()
                    : novelCards.getFriday())
            .saturday(
                novelCardsDTO.getSaturday() != null ? novelCardsDTO.getSaturday()
                    : novelCards.getSaturday())
            .sunday(
                novelCardsDTO.getSunday() != null ? novelCardsDTO.getSunday()
                    : novelCards.getSunday())
            .build();
    }

    @Override
    public void updateCards(NovelCardsDTO novelCardsDTO) {
        novelCardsRepository.save(modelMapper.map(novelCardsDTO, NovelCards.class));
    }

    @Override
    public void deleteCards(Long id) {
        novelCardsRepository.deleteById(id);
    }

    @Override
    public List<NovelCardsDTO> getNovelCardsForSchedule(Long scheduleId) {
        List<NovelCardsDTO> scheduleList = new ArrayList<>();
        return novelCardsRepository.findAllByScheduleId(scheduleId).stream()
            .map(novelCards -> NovelCardsDTO.builder()
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
                .thumbnail(novelCards.getThumbnail())
                .views(novelCards.getViews())
                .serializationStatus(novelCards.getSerializationStatus())
                .description(novelCards.getDescription())
                .scheduleId(novelCards.getScheduleId())
                .startDate(novelCards.getStartDate())
                .starRating(novelCards.getStarRating())
                .monday(novelCards.getMonday())
                .tuesday(novelCards.getTuesday())
                .wednesday(novelCards.getWednesday())
                .thursday(novelCards.getThursday())
                .friday(novelCards.getFriday())
                .saturday(novelCards.getSaturday())
                .sunday(novelCards.getSunday())
                .build())
            .collect(Collectors.toList());
    }
}

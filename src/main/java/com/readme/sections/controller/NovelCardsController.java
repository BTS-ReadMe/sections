package com.readme.sections.controller;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.dto.NovelCardsDTO.Tag;
import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.responseObject.ResponseNovelCards;
import com.readme.sections.responseObject.ResponseNovelCardsPagination;
import com.readme.sections.service.NovelCardsService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cards/novels")
public class NovelCardsController {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int PAGE_SIZE;
    private final NovelCardsService novelCardsService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseNovelCards> getNovelCard(@PathVariable Long id) {
        NovelCardsDTO novelCardsDTO = novelCardsService.getCards(id);
        return ResponseEntity.ok(modelMapper.map(novelCardsDTO, ResponseNovelCards.class));
    }

    @GetMapping
    public ResponseEntity<ResponseNovelCardsPagination> getAllNovelCards(
        @Param("pagination") Integer pagination, Pageable pageable) {
        pageable = PageRequest.of(pagination, PAGE_SIZE);
        NovelCardsPaginationDTO novelCardsPaginationDTO = novelCardsService.getAllCards(pageable);
        return ResponseEntity.ok(ResponseNovelCardsPagination.builder()
            .novelCardsData(novelCardsPaginationDTO.getNovelCardsData())
                .size(novelCardsPaginationDTO.getSize())
                .page(novelCardsPaginationDTO.getPage())
                .totalElements(novelCardsPaginationDTO.getTotalElements())
                .totalPages(novelCardsPaginationDTO.getTotalPages())
                .build());
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ResponseNovelCards>> getNovelCardsForSchedule(
        @Param("scheduleId") Long scheduleId) {
        List<NovelCardsDTO> novelCardsDTOList = novelCardsService.getNovelCardsForSchedule(
            scheduleId);
        return ResponseEntity.ok(novelCardsDTOList.stream()
            .map(novelCardsDTO -> ResponseNovelCards.builder()
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
                .build())
            .collect(Collectors.toList()));
    }
}

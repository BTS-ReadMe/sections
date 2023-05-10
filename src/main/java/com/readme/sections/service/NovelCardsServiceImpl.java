package com.readme.sections.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.dto.NovelCardsDTO.Tag;
import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO.NovelCardsData;
import com.readme.sections.model.NovelCards;
import com.readme.sections.repository.NovelCardsRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NovelCardsServiceImpl implements NovelCardsService {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int PAGE_SIZE;
    private final MongoTemplate mongoTemplate;
    private final NovelCardsRepository novelCardsRepository;
    private final ModelMapper modelMapper;

    public NovelCardsDTO getCards(Long id) {
        NovelCards novelCards = novelCardsRepository.findById(id).get();
        return modelMapper.map(novelCards, NovelCardsDTO.class);
    }

    @Override
    public NovelCardsPaginationDTO getAllCardsByGenre(String genre, Pageable pageable) {
        Slice<NovelCards> novelCardsList = null;
        long totalElements = 0L;
        if (genre.equals("all")) {
            novelCardsList = novelCardsRepository.findAllNovelCards(getOneMonthAgo(), getNow(),
                pageable);
            totalElements = novelCardsRepository.findAll().stream().count();
        } else {
            novelCardsList = novelCardsRepository.findAllByGenre(genre, getOneMonthAgo(), getNow(),
                pageable);
            totalElements = novelCardsRepository.countGenre(genre);
        }
        List<NovelCardsData> novelCardsData = novelCardsList.stream()
            .map(novel -> modelMapper.map(novel, NovelCardsData.class))
            .collect(Collectors.toList());
        return NovelCardsPaginationDTO.builder()
            .novelCardsData(novelCardsData)
            .size(novelCardsList.getSize())
            .page(novelCardsList.getNumber())
            .totalElements(totalElements)
            .totalPages((int) Math.ceil((double) totalElements / (double) novelCardsList.getSize()))
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
        List<NovelCards> novelCardsList = novelCardsRepository.findAllByScheduleId(scheduleId,
            getOneMonthAgo(), getNow());
        return novelCardsList.stream()
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
                .isNew(novelCards.getIsNew())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public NovelCardsPaginationDTO getNewNovels(Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        AggregationOperation[] operations = {
            match(where("startDate").gte(getOneMonthAgo()).lte(getNow())),
            project("novelId", "title", "description", "author", "genre", "grade", "thumbnail",
                "startDate", "views",
                "serializationStatus", "tags", "scheduleId", "starRating", "monday", "tuesday",
                "wednesday", "thursday",
                "friday", "saturday", "sunday", "episodeCount")
                .and(ComparisonOperators.Gt.valueOf("startDate").greaterThanValue(getOneMonthAgo()))
                .lt(ComparisonOperators.Lt.valueOf("startDate").lessThanValue(getNow()))
                .as("isNew"),
            skip(PAGE_SIZE * pagination),
            limit(PAGE_SIZE)
        };

        Long totalElements = mongoTemplate.count(
            Query.query(Criteria.where("startDate").gte(getOneMonthAgo()).lte(getNow())), NovelCards.class);

        TypedAggregation<NovelCards> typedAggregation = Aggregation.<NovelCards>newAggregation(
            NovelCards.class, operations);

        List<NovelCards> results = mongoTemplate.aggregate(typedAggregation, NovelCards.class)
            .getMappedResults();

        return NovelCardsPaginationDTO.builder()
            .novelCardsData(results.stream()
                .map(novel -> modelMapper.map(novel, NovelCardsData.class))
                .collect(Collectors.toList()))
            .size(PAGE_SIZE)
            .page(pagination)
            .totalElements(totalElements)
            .totalPages((int) Math.ceil((double) totalElements / (double) PAGE_SIZE))
            .build();
    }

    private static Date getNow() {
        return new Date();
    }

    private static Date getOneMonthAgo() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        now = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }
}

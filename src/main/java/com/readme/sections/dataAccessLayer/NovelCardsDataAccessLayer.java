package com.readme.sections.dataAccessLayer;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO.NovelCardsData;
import com.readme.sections.model.NovelCards;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RequiredArgsConstructor
public class NovelCardsDataAccessLayer {
    @Value("${spring.data.web.pageable.default-page-size}")
    private int PAGE_SIZE;
    private final MongoTemplate mongoTemplate;
    private final ModelMapper modelMapper;

    public List<NovelCards> getAllNovelCards(Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        AggregationOperation[] operations = {
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

        Long totalElements = mongoTemplate.count(new Query(), NovelCards.class);

        TypedAggregation<NovelCards> typedAggregation = Aggregation.<NovelCards>newAggregation(
            NovelCards.class, operations);

        return mongoTemplate.aggregate(typedAggregation, NovelCards.class)
            .getMappedResults();
    }

    public List<NovelCards> getAllGenre(String genre, Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        AggregationOperation[] operations = {
            match(where("genre").is(genre)),
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
            Query.query(Criteria.where("genre").is(genre)), NovelCards.class);

        TypedAggregation<NovelCards> typedAggregation = Aggregation.<NovelCards>newAggregation(
            NovelCards.class, operations);

        return mongoTemplate.aggregate(typedAggregation, NovelCards.class)
            .getMappedResults();
    }

    public NovelCardsPaginationDTO getNewNovelsData(Integer pagination) {
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
            Query.query(Criteria.where("startDate").gte(getOneMonthAgo()).lte(getNow())),
            NovelCards.class);

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

    public List<NovelCards> getAllByScheduleId(Long scheduleId) {
        AggregationOperation[] operations = {
            match(where("scheduleId").is(scheduleId)),
            project("novelId", "title", "description", "author", "genre", "grade", "thumbnail",
                "startDate", "views",
                "serializationStatus", "tags", "scheduleId", "starRating", "monday", "tuesday",
                "wednesday", "thursday",
                "friday", "saturday", "sunday", "episodeCount")
                .and(ComparisonOperators.Gt.valueOf("startDate").greaterThanValue(getOneMonthAgo()))
                .lt(ComparisonOperators.Lt.valueOf("startDate").lessThanValue(getNow()))
                .as("isNew")
        };

        TypedAggregation<NovelCards> typedAggregation = Aggregation.<NovelCards>newAggregation(
            NovelCards.class, operations);

        return mongoTemplate.aggregate(typedAggregation,
                NovelCards.class)
            .getMappedResults();
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

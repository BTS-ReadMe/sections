package com.readme.sections.dataAccessLayer;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.readme.sections.model.NovelCards;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class NovelCardsDataAccessLayer {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int PAGE_SIZE;
    private final MongoTemplate mongoTemplate;

    public Page<NovelCards> findByDayTrue(String day, Integer pagination) {
        Pageable pageable = PageRequest.of(pagination, PAGE_SIZE);

        Query query = new Query();
        query.addCriteria(Criteria.where(getSerializationName(day)).is(true)).with(pageable);

        List<NovelCards> novelCardsList = mongoTemplate.find(query, NovelCards.class);

        return new PageImpl<>(novelCardsList, pageable, mongoTemplate.count(query, NovelCards.class));
    }

    public List<NovelCards> getAllNovelCardsData(Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        AggregationOperation[] operations = {
            project("novelId", "title", "description", "author", "genre", "grade", "thumbnail",
                "startDate", "views",
                "serializationStatus", "tags", "scheduleId", "starRating", "monday", "tuesday",
                "wednesday", "thursday",
                "friday", "saturday", "sunday", "episodeCount"),
            skip(PAGE_SIZE * pagination),
            limit(PAGE_SIZE)
        };

        TypedAggregation<NovelCards> typedAggregation = Aggregation.<NovelCards>newAggregation(
            NovelCards.class, operations);

        return mongoTemplate.aggregate(typedAggregation, NovelCards.class)
            .getMappedResults();
    }

    public Long getAllNovelCardsData() {
        return mongoTemplate.count(new Query(), NovelCards.class);
    }

    public List<NovelCards> getAllByScheduleIdData(Long scheduleId) {
        AggregationOperation[] operations = {
            match(where("scheduleId").is(scheduleId)),
            project("novelId", "title", "description", "author", "genre", "grade", "thumbnail",
                "startDate", "views",
                "serializationStatus", "tags", "scheduleId", "starRating", "monday", "tuesday",
                "wednesday", "thursday",
                "friday", "saturday", "sunday", "episodeCount")
        };

        TypedAggregation<NovelCards> typedAggregation = Aggregation.<NovelCards>newAggregation(
            NovelCards.class, operations);

        return mongoTemplate.aggregate(typedAggregation,
                NovelCards.class)
            .getMappedResults();
    }

    public String getSerializationName(String serializationDays) {
        switch (serializationDays) {
            case "월":
                serializationDays = "monday";
                break;

            case "화":
                serializationDays = "tuesday";
                break;

            case "수":
                serializationDays = "wednesday";
                break;

            case "목":
                serializationDays = "thursday";
                break;

            case "금":
                serializationDays = "friday";
                break;

            case "토":
                serializationDays = "saturday";
                break;

            case "일":
                serializationDays = "sunday";
                break;
        }
        return serializationDays;
    }
}

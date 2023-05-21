package com.readme.sections.dataAccessLayer;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;

import com.readme.sections.enums.SerializationDays;
import com.readme.sections.model.NovelCards;
import java.util.List;
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
        query.addCriteria(Criteria.where(SerializationDays.fromKorean(day).getEnglishDay()).is(true)).with(pageable);

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
}

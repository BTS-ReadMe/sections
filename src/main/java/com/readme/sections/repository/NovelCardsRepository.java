package com.readme.sections.repository;

import com.readme.sections.model.NovelCards;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelCardsRepository extends MongoRepository<NovelCards, Long> {

    @Aggregation({
        "{$match: {scheduleId: ?0}}",
        "{$project: {_id: 1, title: 1, description: 1, author: 1, genre: 1, grade: 1, thumbnail: 1,"
            + "startDate: 1, views: 1, serializationStatus: 1, tags: 1, scheduleId: 1, starRating: 1,"
            + "monday: 1, tuesday: 1, wednesday: 1, thursday: 1, friday: 1, saturday: 1, sunday: 1}}",
        "{$addFields: {isNew: {$cond: [{$and: [{$gt: ['$startDate', ?1]}, "
            + "{$lt: ['$startDate', ?2]}]}, true, false]}}}"
    })
    List<NovelCards> findAllByScheduleId(Long ScheduleId, Date oneWeekAgo, Date now);

    Page<NovelCards> findAll(Pageable pageable);

    Page<NovelCards> findAllByGenre(String genre, Pageable pageable);
}

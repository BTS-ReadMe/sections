package com.readme.sections.repository;

import com.readme.sections.model.NovelCards;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    @Aggregation({
        "{$lookup: {\n" +
            "        from: 'episode_cards',\n" +
            "        localField: '_id',\n" +
            "        foreignField: '_id',\n" +
            "        as: 'episodes'\n" +
            "    }}",
        "{$addFields: {\n" +
            "        episodeFields: {$map: {\n" +
            "            input: '$episode.episodes',\n" +
            "            as: 'episode',\n" +
            "            in: {$size: {$objectToArray: '$$episode'}}\n" +
            "        }}\n" +
            "    }}",
        "{$project: {\n" +
            "        _id: 0,\n" +
            "        novelId: '$_id',\n" +
            "        title: 1,\n" +
            "        description: 1,\n" +
            "        author: 1,\n" +
            "        genre: 1,\n" +
            "        grade: 1,\n" +
            "        thumbnail: 1,\n" +
            "        startDate: 1,\n" +
            "        views: 1,\n" +
            "        serializationStatus: 1,\n" +
            "        tags: 1,\n" +
            "        scheduleId: 1,\n" +
            "        starRating: 1,\n" +
            "        monday: 1,\n" +
            "        tuesday: 1,\n" +
            "        wednesday: 1,\n" +
            "        thursday: 1,\n" +
            "        friday: 1,\n" +
            "        saturday: 1,\n" +
            "        sunday: 1,\n" +
            "        isNew: {$cond: [{$and: [{$gt: ['$startDate', ?0]}, {$lt: ['$startDate', ?1]}]}, true, false]},\n" +
            "        episodeCount: {$size: {$arrayElemAt: [\"$episodes.episodes\", 0]}}\n" +
            "    }}"
    })
    Slice<NovelCards> findAllNovelCards(Date oneWeekAgo, Date now, Pageable pageable);

    @Aggregation({
        "{$match: {genre: ?0}}",
        "{$lookup: {\n" +
            "        from: 'episode_cards',\n" +
            "        localField: '_id',\n" +
            "        foreignField: '_id',\n" +
            "        as: 'episodes'\n" +
            "    }}",
        "{$addFields: {\n" +
            "        episodeFields: {$map: {\n" +
            "            input: '$episode.episodes',\n" +
            "            as: 'episode',\n" +
            "            in: {$size: {$objectToArray: '$$episode'}}\n" +
            "        }}\n" +
            "    }}",
        "{$project: {\n" +
            "        _id: 0,\n" +
            "        novelId: '$_id',\n" +
            "        title: 1,\n" +
            "        description: 1,\n" +
            "        author: 1,\n" +
            "        genre: 1,\n" +
            "        grade: 1,\n" +
            "        thumbnail: 1,\n" +
            "        startDate: 1,\n" +
            "        views: 1,\n" +
            "        serializationStatus: 1,\n" +
            "        tags: 1,\n" +
            "        scheduleId: 1,\n" +
            "        starRating: 1,\n" +
            "        monday: 1,\n" +
            "        tuesday: 1,\n" +
            "        wednesday: 1,\n" +
            "        thursday: 1,\n" +
            "        friday: 1,\n" +
            "        saturday: 1,\n" +
            "        sunday: 1,\n" +
            "        isNew: {$cond: [{$and: [{$gt: ['$startDate', ?1]}, {$lt: ['$startDate', ?2]}]}, true, false]},\n" +
            "        episodeCount: {$size: {$arrayElemAt: [\"$episodes.episodes\", 0]}}\n" +
            "    }}"
    })
    Slice<NovelCards> findAllByGenre(String genre, Date oneWeekAgo, Date now, Pageable pageable);
}

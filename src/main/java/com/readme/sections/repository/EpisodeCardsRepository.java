package com.readme.sections.repository;

import com.readme.sections.model.EpisodeCards;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeCardsRepository extends MongoRepository<EpisodeCards, Long> {
    @Aggregation({
        "{$match: {_id: ?0}}\n",
        "{$project: {"
            + "_id: 1,"
            + "episodes: {"
            + "$slice:['$episodes', skipValue: ?1, limitValue"
    })
    Slice<EpisodeCards> findEpisodeCardsByNovelId(Long id);
}

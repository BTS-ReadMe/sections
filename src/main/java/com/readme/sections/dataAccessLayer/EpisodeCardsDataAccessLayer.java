package com.readme.sections.dataAccessLayer;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.readme.sections.model.EpisodeCards;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Size;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Slice;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EpisodeCardsDataAccessLayer {
    private final MongoTemplate mongoTemplate;
    public EpisodeCards findEpisodesByEpisodeCardId(Long novelId, Integer skipValue,
        Integer limitValue) {

        MatchOperation match = match(where("_id").is(novelId));
        ProjectionOperation project = Aggregation.project()
            .and(Slice.sliceArrayOf("$episodes").offset(skipValue).itemCount(limitValue))
            .as("episodes")
            .and(Size.lengthOfArray("$episodes")).as("episodeCount");

        Aggregation aggregation = newAggregation(match, project);

        AggregationResults<EpisodeCards> results = mongoTemplate.aggregate(aggregation,
            "episode_cards", EpisodeCards.class);

        return results.getUniqueMappedResult();
    }
}

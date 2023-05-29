package com.readme.sections.dataAccessLayer;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.readme.sections.dto.EpisodeDTO;
import com.readme.sections.model.EpisodeCards;
import com.readme.sections.model.EpisodeCards.Episode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Size;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Slice;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EpisodeCardsDataAccessLayer {
    private final MongoTemplate mongoTemplate;
    public EpisodeCards findEpisodesByEpisodeCardId(Long novelId, Integer skipValue,
        Integer limitValue) {

        MatchOperation match = match(where("_id").is(String.valueOf(novelId)));
        ProjectionOperation project = Aggregation.project()
            .and(Slice.sliceArrayOf("$episodes").offset(skipValue).itemCount(limitValue))
            .as("episodes")
            .and(Size.lengthOfArray("$episodes")).as("episodeCount");

        Aggregation aggregation = newAggregation(match, project);

        AggregationResults<EpisodeCards> results = mongoTemplate.aggregate(aggregation,
            "episode_cards", EpisodeCards.class);

        log.info(results.getUniqueMappedResult().toString());
        return results.getUniqueMappedResult();
    }

    public void addEpisode(EpisodeDTO episodeDTO) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(episodeDTO.getNovelId()));

        Update update = new Update();
        update.push("episodes", new Episode(episodeDTO));

        mongoTemplate.updateFirst(query, update, EpisodeCards.class);
    }

    public void updateEpisode(EpisodeDTO episodeDTO) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(episodeDTO.getNovelId()).and("episodes._id").is(episodeDTO.getEpisodeId()));

        Update update = new Update();
        update.set("episodes.$.fieldToChange", new Episode(episodeDTO));

        mongoTemplate.updateFirst(query, update, EpisodeCards.class);
    }
}

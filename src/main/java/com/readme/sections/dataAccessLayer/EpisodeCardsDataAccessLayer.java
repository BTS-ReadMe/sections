package com.readme.sections.dataAccessLayer;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mongodb.BasicDBObject;
import com.readme.sections.dto.EpisodeDTO;
import com.readme.sections.enums.EpisodeSort;
import com.readme.sections.model.EpisodeCards;
import com.readme.sections.model.EpisodeCards.Episode;
import com.readme.sections.requestObject.RequestDeleteEpisode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Size;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Slice;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
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
        Integer limitValue, String sortKey) {
        MatchOperation match = match(where("_id").is(String.valueOf(novelId)));
        UnwindOperation unwind = Aggregation.unwind("episodes");
        SortOperation sort = Aggregation.sort(EpisodeSort.getSortValueFromKey(sortKey), "episodes.registrationDate");
        GroupOperation group = Aggregation.group("_id")
            .push("episodes").as("episodes")
            .first("episodeCount").as("episodeCount");

        ProjectionOperation project = Aggregation.project()
            .andExpression("slice(episodes, " + skipValue + ", " + limitValue + ")").as("episodes")
            .and(Size.lengthOfArray("$episodes")).as("episodeCount");

        Aggregation aggregation = newAggregation(match, unwind, sort, group, project);

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
        query.addCriteria(Criteria.where("_id").is(episodeDTO.getNovelId())
            .andOperator(Criteria.where("episodes._id").is(episodeDTO.getEpisodeId())));

        Update update = new Update();
        update.set("episodes.$", new Episode(episodeDTO));

        mongoTemplate.updateFirst(query, update, EpisodeCards.class);
    }

    public void deleteEpisode(RequestDeleteEpisode requestDeleteEpisode) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(String.valueOf(requestDeleteEpisode.getNovelId())));

        Update update = new Update();
        update.pull("episodes", new BasicDBObject("_id", requestDeleteEpisode.getEpisodeId()));

        mongoTemplate.updateFirst(query, update, EpisodeCards.class);
    }
}

package com.readme.sections.repository;

import com.readme.sections.model.EpisodeCards;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeCardsRepository extends MongoRepository<EpisodeCards, Long> {

}

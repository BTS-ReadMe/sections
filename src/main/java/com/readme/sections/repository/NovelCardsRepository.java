package com.readme.sections.repository;

import com.readme.sections.model.NovelCards;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelCardsRepository extends MongoRepository<NovelCards, Long> {
    List<NovelCards> findAllByTagsNameContaining(String name);
}

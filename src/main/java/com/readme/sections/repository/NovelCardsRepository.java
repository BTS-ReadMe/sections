package com.readme.sections.repository;

import com.readme.sections.model.NovelCards;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelCardsRepository extends MongoRepository<NovelCards, Long> {
    Page<NovelCards> findAllByTagsNameOrTitleContaining(String keyword, String keyword2, Pageable pageable);
    Page<NovelCards> findAllByGenreAndSerializationStatus(String genre, String serializationStatus, Pageable pageable);
}

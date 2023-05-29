package com.readme.sections.repository;

import com.readme.sections.model.NovelCards;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelCardsRepository extends MongoRepository<NovelCards, String> {
    Page<NovelCards> findAllByTagsNameOrTitleContaining(String keyword, String keyword2, Pageable pageable);
    Page<NovelCards> findAllByGenreAndSerializationStatus(String genre, String serializationStatus, Pageable pageable);
    Page<NovelCards> findAllByGenreAndStartDateBetween(String genre, Date oneMonthAgo, Date now, Pageable pageable);
    List<NovelCards> findAllByScheduleId(Long scheduleId);
    Page<NovelCards> findAll(Pageable pageable);
}

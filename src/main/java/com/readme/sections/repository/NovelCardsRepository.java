package com.readme.sections.repository;

import com.readme.sections.model.NovelCards;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelCardsRepository extends MongoRepository<NovelCards, Long> {
    List<NovelCards> findAllByScheduleId(Long ScheduleId);
    Page<NovelCards> findAll(Pageable pageable);
    Page<NovelCards> findAllByGenre(String genre,Pageable pageable);
}

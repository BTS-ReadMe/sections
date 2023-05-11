package com.readme.sections.service;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.model.NovelCards;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface NovelCardsService {
    public NovelCardsDTO getCards(Long id);
    public NovelCardsPaginationDTO getAllCardsByGenre(String genre, String serializationStatus, Integer pagination);
    public NovelCardsPaginationDTO getNewNovelsByGenre(String genre, Integer pagination);
    public void addCards(NovelCardsDTO novelCardsDTO);
    public void updateCards(NovelCardsDTO novelCardsDTO);
    public NovelCardsDTO existUpdateData(Long id, NovelCardsDTO novelCardsDTO);
    public void deleteCards(Long id);
    public List<NovelCardsDTO> getNovelCardsForSchedule(Long scheduleId);
    public List<NovelCards> searchTags(String tag);
    NovelCardsPaginationDTO getAllCardsBySerializationDays(String serializationDays, Integer pagination);
}

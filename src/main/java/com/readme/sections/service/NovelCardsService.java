package com.readme.sections.service;

import com.readme.sections.dto.NovelCardsEntityDTO;
import com.readme.sections.dto.NovelCardsViewDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.model.NovelCards;
import java.util.List;

public interface NovelCardsService {
    public NovelCardsViewDTO getCards(Long id);
    public NovelCardsPaginationDTO getAllCardsByGenre(String genre, String serializationStatus, Integer pagination);
    public NovelCardsPaginationDTO getNewNovelsByGenre(String genre, Integer pagination);
    public void addCards(NovelCardsEntityDTO novelCardsEntityDTO);
    public void updateCards(NovelCardsEntityDTO novelCardsEntityDTO);
    public NovelCardsEntityDTO existUpdateData(Long id, NovelCardsEntityDTO novelCardsEntityDTO);
    public void deleteCards(Long id);
    public List<NovelCardsViewDTO> getNovelCardsForSchedule(Long scheduleId);
    public List<NovelCards> searchTags(String tag);
    NovelCardsPaginationDTO getAllCardsBySerializationDays(String serializationDays, Integer pagination);
}

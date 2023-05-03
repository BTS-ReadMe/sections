package com.readme.sections.service;

import com.readme.sections.dto.NovelCardsDTO;
import java.util.List;

public interface NovelCardsService {
    public NovelCardsDTO getCards(Long id);
    public void addCards(NovelCardsDTO novelCardsDTO);
    public void updateCards(NovelCardsDTO novelCardsDTO);
    public NovelCardsDTO existUpdateData(Long id, NovelCardsDTO novelCardsDTO);
    public void deleteCards(Long id);
    public List<NovelCardsDTO> getNovelCardsForSchedule(Long scheduleId);
}

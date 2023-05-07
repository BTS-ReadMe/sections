package com.readme.sections.service;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.dto.NovelCardsSliceDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface NovelCardsService {
    public NovelCardsDTO getCards(Long id);
    public NovelCardsSliceDTO getAllCards(Pageable pageable);
    public NovelCardsSliceDTO getAllCardsByGenre(String genre, Pageable pageable);
    public void addCards(NovelCardsDTO novelCardsDTO);
    public void updateCards(NovelCardsDTO novelCardsDTO);
    public NovelCardsDTO existUpdateData(Long id, NovelCardsDTO novelCardsDTO);
    public void deleteCards(Long id);
    public List<NovelCardsDTO> getNovelCardsForSchedule(Long scheduleId);
}

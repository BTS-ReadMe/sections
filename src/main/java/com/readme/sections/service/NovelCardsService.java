package com.readme.sections.service;

import com.readme.sections.dto.ResponseNovelCards;
import com.readme.sections.vo.RequestNovelCards;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface NovelCardsService {
    public ResponseNovelCards getCards(Long id);
    public void addCards(RequestNovelCards requestNovelCards);
    public void updateCards(Long id, RequestNovelCards requestNovelCards) throws NotFoundException;
    public void deleteCards(Long id);
}

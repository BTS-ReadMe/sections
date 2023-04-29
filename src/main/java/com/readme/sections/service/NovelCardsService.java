package com.readme.sections.service;

import com.readme.sections.model.NovelCards;
import com.readme.sections.repository.NovelCardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelCardsService {
    private final NovelCardsRepository novelCardsRepository;

    public NovelCards addCards() {
        return novelCardsRepository.insert(NovelCards.builder()
            .novelId(0L)
            .title("나 혼자만 레벨업")
            .build());
    }
}

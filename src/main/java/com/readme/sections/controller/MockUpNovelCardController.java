package com.readme.sections.controller;

import com.readme.sections.service.NovelCardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sections-service/novel-cards")
public class MockUpNovelCardController {
    private final NovelCardsService novelCardsService;
    @GetMapping
    public void addNovelCard() {
        novelCardsService.addCards();
    }
}

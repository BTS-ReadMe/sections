package com.readme.sections.controller;

import com.readme.sections.service.NovelCardsServiceImple;
import com.readme.sections.vo.RequestNovelCards;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/cards/novels")
public class AdminNovelCardsController {
    private final NovelCardsServiceImple novelCardsServiceImple;
    @PostMapping
    public void addNovelCard(@RequestBody RequestNovelCards requestNovelCards) {
        novelCardsServiceImple.addCards(requestNovelCards);
    }
}

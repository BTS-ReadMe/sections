package com.readme.sections.controller;

import com.readme.sections.cqrs.CreateNovelCardCommand;
import com.readme.sections.cqrs.NovelCardsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CQRSController {
    private final NovelCardsCommandService novelCardsCommandService;

    @PostMapping("/novelCards")
    public void createNovelCards(@RequestBody CreateNovelCardCommand command) {
        novelCardsCommandService.createNovelCard(command);
    }

}

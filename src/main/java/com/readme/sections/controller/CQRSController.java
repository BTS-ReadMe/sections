//package com.readme.sections.controller;
//
//import com.readme.sections.cqrs.CreateNovelCardCommand;
//import com.readme.sections.cqrs.NovelCardsCommandService;
//import com.readme.sections.cqrs.NovelCardsQueryService;
//import com.readme.sections.model.NovelCards;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class CQRSController {
//    private final NovelCardsCommandService novelCardsCommandService;
//    private final NovelCardsQueryService novelCardsQueryService;
//
//    @PostMapping("/novelCards")
//    public void createNovelCards(@RequestBody CreateNovelCardCommand command) {
//        novelCardsCommandService.createNovelCard(command);
//    }
//
//    @GetMapping("/novelCards/{novelId}")
//    public NovelCards findNovelCard(@PathVariable Long novelId) {
//        return novelCardsQueryService.findNovelCard(String.valueOf(novelId));
//    }
//}

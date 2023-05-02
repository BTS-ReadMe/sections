package com.readme.sections.controller;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.service.NovelCardsService;
import com.readme.sections.requestObject.RequestNovelCards;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/cards/novels")
@Slf4j
public class AdminNovelCardsController {
    private final NovelCardsService novelCardsService;
    private final ModelMapper modelMapper;

    @PostMapping
    public void addNovelCard(@RequestBody RequestNovelCards requestNovelCards) {
        NovelCardsDTO novelCardsDTO = modelMapper.map(requestNovelCards, NovelCardsDTO.class);
        novelCardsService.addCards(novelCardsDTO);
    }

    @PatchMapping("/{id}")
    public void updateNovelCard(@PathVariable Long id, @RequestBody RequestNovelCards requestNovelCards) {
        NovelCardsDTO novelCardsDTO = modelMapper.map(requestNovelCards, NovelCardsDTO.class);
        novelCardsService.updateCards(novelCardsService.existUpdateData(id, novelCardsDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteNovelCard(@PathVariable Long id) {
        novelCardsService.deleteCards(id);
    }

}

package com.readme.sections.controller;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.responseObject.ResponseNovelCards;
import com.readme.sections.service.NovelCardsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cards/novels")
public class NovelCardsController {
    private final NovelCardsService novelCardsService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseNovelCards> getNovelCard(@PathVariable Long id) {
        NovelCardsDTO novelCardsDTO = novelCardsService.getCards(id);
        return ResponseEntity.ok(modelMapper.map(novelCardsDTO, ResponseNovelCards.class));
    }
}

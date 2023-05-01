package com.readme.sections.controller;

import com.readme.sections.dto.NovelCardsDTO;
import com.readme.sections.service.NovelCardsServiceImple;
import com.readme.sections.vo.RequestNovelCards;
import com.readme.sections.vo.ResponseNovelCards;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final NovelCardsServiceImple novelCardsServiceImple;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseNovelCards> getNovelCard(@PathVariable Long id) {
        NovelCardsDTO novelCardsDTO = novelCardsServiceImple.getCards(id);
        return ResponseEntity.ok(modelMapper.map(novelCardsDTO, ResponseNovelCards.class));
    }

    @PostMapping
    public void addNovelCard(@RequestBody RequestNovelCards requestNovelCards) {
        NovelCardsDTO novelCardsDTO = modelMapper.map(requestNovelCards, NovelCardsDTO.class);
        novelCardsServiceImple.addCards(novelCardsDTO);
    }

    @PatchMapping("/{id}")
    public void updateNovelCard(@PathVariable Long id, @RequestBody RequestNovelCards requestNovelCards) {
        NovelCardsDTO novelCardsDTO = modelMapper.map(requestNovelCards, NovelCardsDTO.class);
        novelCardsServiceImple.updateCards(novelCardsServiceImple.existUpdateData(id, novelCardsDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteNovelCard(@PathVariable Long id) {
        novelCardsServiceImple.deleteCards(id);
    }

}

//package com.readme.sections.cqrs;
//
//import com.readme.sections.model.NovelCards;
//import com.readme.sections.repository.NovelCardsRepository;
//import java.util.Optional;
//import lombok.RequiredArgsConstructor;
//import org.axonframework.config.ProcessingGroup;
//import org.axonframework.eventhandling.EventHandler;
//import org.axonframework.queryhandling.QueryHandler;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Component;
//
//@Component
//@ProcessingGroup("novel_cards_group")
//@RequiredArgsConstructor
//public class NovelCardsProjection {
//    private final NovelCardsRepository novelCardsRepository;
//
//    @EventHandler
//    public void on(NovelCardCreatedEvent evt) {
//        NovelCards novelCards = new NovelCards(evt);
//        novelCardsRepository.save(novelCards);
//    }
//
//    @QueryHandler
//    public NovelCards handle(@NotNull FindNovelCardQuery query) {
//        Optional<NovelCards> novelCardsOptional = novelCardsRepository.findById(query.getNovelId());
//
//        if (!novelCardsOptional.isPresent()) {
//            throw new RuntimeException("NovelCard not found for given id");
//        }
//
//        NovelCards novelCards = novelCardsOptional.get();
//
//        return novelCards;
//    }
//}

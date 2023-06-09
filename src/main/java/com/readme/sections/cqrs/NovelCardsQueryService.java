//package com.readme.sections.cqrs;
//
//import com.readme.sections.model.NovelCards;
//import lombok.RequiredArgsConstructor;
//import org.axonframework.messaging.responsetypes.ResponseTypes;
//import org.axonframework.queryhandling.QueryGateway;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class NovelCardsQueryService {
//    private final QueryGateway queryGateway;
//
//    public NovelCards findNovelCard(String novelId) {
//        return queryGateway.query(new FindNovelCardQuery(novelId), ResponseTypes.instanceOf(NovelCards.class)).join();
//    }
//}

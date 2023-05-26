package com.readme.sections.service;

import com.readme.sections.dto.NovelCardsEntityDTO;
import com.readme.sections.requestObject.RequestNovelCards;
import com.readme.sections.requestObject.RequestNovelId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaConsumer {

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final NovelCardsService novelCardsService;

//    @Scheduled(fixedRate = 60000)
    public void consumeMessagesPeriodically() {
        // Kafka Listener를 시작
        kafkaListenerEndpointRegistry.getListenerContainer("yourListenerId").start();

        // 일정 시간(예: 10초) 동안 메시지를 소비하도록 대기
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Kafka Listener를 중지
        kafkaListenerEndpointRegistry.getListenerContainer("yourListenerId").stop();
    }

//    @KafkaListener(id = "yourListenerId", topics = "test_topic")
//    public void listen(RequestKafkaMessage vo) {
//        System.out.println("name = " + vo.getName());
//        System.out.println("consume message = " + vo.getMsg());
//    }

    @KafkaListener(topics = "addNovels", groupId = "sections")
    public void addNovelCards(RequestNovelCards requestNovelCards){
        novelCardsService.addCards(new NovelCardsEntityDTO(requestNovelCards));
    }

    @KafkaListener(topics = "updateNovels", groupId = "sections")
    public void updateNovelCards(RequestNovelCards requestNovelCards){
        novelCardsService.updateCards(new NovelCardsEntityDTO(requestNovelCards));
    }

    @KafkaListener(topics = "deleteNovels", groupId = "sections")
    public void deleteNovelCards(RequestNovelId requestNovelId){
        novelCardsService.deleteCards(requestNovelId.getId());
    }
}
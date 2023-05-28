package com.readme.sections.service;

import com.readme.sections.requestObject.RequestKafkaNovelCards;
import com.readme.sections.requestObject.RequestKafkaNovelId;
import com.readme.sections.requestObject.RequestNovelId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private static final String TOPIC = "deleteNovels";
    private final KafkaTemplate<String, RequestKafkaNovelCards> kafkaTemplate;
    private final KafkaTemplate<String, RequestKafkaNovelId> novelIdKafkaTemplate;

    public void sendNovelId(RequestKafkaNovelId requestKafkaNovelId) {
        System.out.println(String.format("Produce message(RequestKafkaMessage) : %s", requestKafkaNovelId));
        this.novelIdKafkaTemplate.send(TOPIC, requestKafkaNovelId);
    }

    public void sendMessage(RequestKafkaNovelCards message) {
        System.out.println(String.format("Produce message(RequestKafkaMessage) : %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}

package com.readme.sections.service;

import com.readme.sections.requestObject.RequestNovelCards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final String TOPIC = "addNovels";
    private final KafkaTemplate<String, RequestNovelCards> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RequestNovelCards message) {
        System.out.println(String.format("Produce message(RequestKafkaMessage) : %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}

package com.readme.sections.service;

import com.readme.sections.requestObject.RequestKafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final String TOPIC = "test_topic";
    private final KafkaTemplate<String, RequestKafkaMessage> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RequestKafkaMessage message) {
        System.out.println(String.format("Produce message(RequestKafkaMessage) : %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}

package com.readme.sections.service;

import com.readme.sections.requestObject.RequestKafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Scheduled(fixedRate = 60000)
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

    @KafkaListener(id = "yourListenerId", topics = "test_topic")
    public void listen(RequestKafkaMessage vo) {
        System.out.println("name = " + vo.getName());
        System.out.println("consume message = " + vo.getMsg());
    }

//    @KafkaListener(topics = "test_topic", groupId = "foo")
    public void consume(RequestKafkaMessage vo){
        System.out.println("name = " + vo.getName());
        System.out.println("consume message = " + vo.getMsg());
    }
}
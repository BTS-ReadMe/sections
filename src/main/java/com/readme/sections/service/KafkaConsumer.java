package com.readme.sections.service;

import com.readme.sections.requestObject.RequestKafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test_topic", groupId = "foo")
    public void consume(RequestKafkaMessage vo){
        System.out.println("name = " + vo.getName());
        System.out.println("consume message = " + vo.getMsg());
    }
}
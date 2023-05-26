package com.readme.sections.controller;

import com.readme.sections.requestObject.RequestKafkaNovelCards;
import com.readme.sections.requestObject.RequestNovelCards;
import com.readme.sections.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final KafkaProducer producer;

    @Autowired
    KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessageJson(@RequestBody RequestKafkaNovelCards message) {
        this.producer.sendMessage(message);
        return "success";
    }
}
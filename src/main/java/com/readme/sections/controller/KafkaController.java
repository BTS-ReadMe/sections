package com.readme.sections.controller;

import com.readme.sections.requestObject.RequestDeleteEpisode;
import com.readme.sections.requestObject.RequestKafkaDeleteEpisode;
import com.readme.sections.requestObject.RequestKafkaEpisode;
import com.readme.sections.requestObject.RequestKafkaNovelCards;
import com.readme.sections.requestObject.RequestKafkaNovelId;
import com.readme.sections.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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

//    @PostMapping
//    public String sendMessageJson(@RequestBody RequestKafkaNovelCards message) {
//        this.producer.sendMessage(message);
//        return "success";
//    }
//
//    @PostMapping("/test")
//    public String novelId(@RequestBody RequestKafkaNovelId message) {
//        this.producer.sendNovelId(message);
//        return "success";
//    }
//
//    @PostMapping("episode")
//    public void episode(@RequestBody RequestKafkaEpisode episode) {
//        this.producer.sendEpisode(episode);
//    }
//
//    @PostMapping("update-episode")
//    public void updateEpisode(@RequestBody RequestKafkaEpisode episode) {
//        this.producer.updateEpisode(episode);
//    }
//
//    @DeleteMapping("episode")
//    public void deleteEpisode(@RequestBody RequestKafkaDeleteEpisode requestDeleteEpisode) {
//        this.producer.deleteEpisode(requestDeleteEpisode);
//    }
}
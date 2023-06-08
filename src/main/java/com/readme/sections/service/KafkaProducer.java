package com.readme.sections.service;

import com.readme.sections.requestObject.RequestDeleteEpisode;
import com.readme.sections.requestObject.RequestEpisode;
import com.readme.sections.requestObject.RequestKafkaDeleteEpisode;
import com.readme.sections.requestObject.RequestKafkaEpisode;
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
    private final KafkaTemplate<String, RequestKafkaEpisode> episodeKafkaTemplate;
    private final KafkaTemplate<String, RequestKafkaDeleteEpisode> deleteEpisodeKafkaTemplate;
    private final KafkaTemplate<String, String> searchCountKafkaTemplate;

    public void sendNovelId(RequestKafkaNovelId requestKafkaNovelId) {
        System.out.println(String.format("Produce message(RequestKafkaMessage) : %s", requestKafkaNovelId));
        this.novelIdKafkaTemplate.send("deleteNovels", requestKafkaNovelId);
    }

    public void sendMessage(RequestKafkaNovelCards message) {
        System.out.println(String.format("Produce message(RequestKafkaMessage) : %s", message));
        this.kafkaTemplate.send("addNovels", message);
    }

    public void sendEpisode(RequestKafkaEpisode requestEpisode) {
        this.episodeKafkaTemplate.send("addEpisodes", requestEpisode);
    }

    public void updateEpisode(RequestKafkaEpisode requestEpisode) {
        this.episodeKafkaTemplate.send("updateEpisodes", requestEpisode);
    }

    public void deleteEpisode(RequestKafkaDeleteEpisode requestDeleteEpisode) {
        this.deleteEpisodeKafkaTemplate.send("deleteEpisodes", requestDeleteEpisode);
    }

    public void searchCount(String keyword) {
        this.searchCountKafkaTemplate.send("inputSearch", keyword);
    }
}

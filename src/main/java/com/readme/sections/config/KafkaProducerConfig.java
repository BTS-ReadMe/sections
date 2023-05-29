package com.readme.sections.config;

import com.readme.sections.requestObject.RequestEpisode;
import com.readme.sections.requestObject.RequestKafkaEpisode;
import com.readme.sections.requestObject.RequestKafkaNovelCards;
import com.readme.sections.requestObject.RequestKafkaNovelId;
import com.readme.sections.requestObject.RequestNovelId;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Bean
    public Map<String, Object> producerConfigs() {
        return CommonJsonSerializer.getStringObjectMap(servers);
    }

    @Bean
    public ProducerFactory<String, RequestKafkaNovelId> novelIdProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public ProducerFactory<String, RequestKafkaNovelCards> novelCardsProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public ProducerFactory<String, RequestKafkaEpisode> episodeProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, RequestKafkaNovelId> novelIdKafkaTemplate() {
        return new KafkaTemplate<>(novelIdProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, RequestKafkaNovelCards> novelCardsKafkaTemplate() {
        return new KafkaTemplate<>(novelCardsProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, RequestKafkaEpisode> episodeKafkaTemplate() {
        return new KafkaTemplate<>(episodeProducerFactory());
    }
}

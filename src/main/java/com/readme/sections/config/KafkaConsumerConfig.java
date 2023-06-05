package com.readme.sections.config;

import com.readme.sections.requestObject.RequestDeleteEpisode;
import com.readme.sections.requestObject.RequestEpisode;
import com.readme.sections.requestObject.RequestDeleteEpisode;
import com.readme.sections.requestObject.RequestEpisode;
import com.readme.sections.requestObject.RequestKafkaStarRating;
import com.readme.sections.requestObject.RequestNovelCards;
import com.readme.sections.requestObject.RequestNovelId;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Bean
    public Map<String, Object> ConsumerConfig() {
        return CommonJsonDeserializer.getStringObjectMap(servers);
    }

    @Bean
    public ConsumerFactory<String, RequestNovelId> novelIdConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(ConsumerConfig());
    }

    @Bean
    public ConsumerFactory<String, RequestNovelCards> novelCardsConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(ConsumerConfig());
    }

    @Bean
    public ConsumerFactory<String, RequestEpisode> episodeConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(ConsumerConfig());
    }

    @Bean
    public ConsumerFactory<String, RequestDeleteEpisode> episodeDeleteConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(ConsumerConfig());
    }

    @Bean
    public ConsumerFactory<String, RequestKafkaStarRating> starRatingConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(ConsumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestNovelId> novelIdListener() {
        ConcurrentKafkaListenerContainerFactory<String, RequestNovelId> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(novelIdConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestNovelCards> novelCardsListener() {
        ConcurrentKafkaListenerContainerFactory<String, RequestNovelCards> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(novelCardsConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestEpisode> episodeListener() {
        ConcurrentKafkaListenerContainerFactory<String, RequestEpisode> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(episodeConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestDeleteEpisode> deleteEpisodeListener() {
        ConcurrentKafkaListenerContainerFactory<String, RequestDeleteEpisode> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(episodeDeleteConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestKafkaStarRating> startRatingListener() {
        ConcurrentKafkaListenerContainerFactory<String, RequestKafkaStarRating> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(starRatingConsumerFactory());
        return factory;
    }

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

}

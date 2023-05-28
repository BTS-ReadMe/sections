package com.readme.sections.config;

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
    public Map<String, Object> novelCardsConsumerConfigs() {
        return CommonJsonDeserializer.getStringObjectMap(servers);
    }

    @Bean
    public ConsumerFactory<String, RequestNovelId> novelIdConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(novelCardsConsumerConfigs());
    }

    @Bean
    public ConsumerFactory<String, RequestNovelCards> novelCardsConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(novelCardsConsumerConfigs());
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
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

}

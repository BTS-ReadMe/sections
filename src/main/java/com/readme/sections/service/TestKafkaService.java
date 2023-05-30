package com.readme.sections.service;

import com.readme.sections.requestObject.RequestKafkaTest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class TestKafkaService {
    private Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final KafkaProducer kafkaProducer;

    public SseEmitter subscribe(String userId) {
        SseEmitter emitter = new SseEmitter();
        RequestKafkaTest requestKafkaTest = new RequestKafkaTest();
        requestKafkaTest.setUserId(userId);
        requestKafkaTest.setMessage(userId + "시작합니다.");
        kafkaProducer.kafkaTest(requestKafkaTest);
        this.emitters.put(userId, emitter);
        return emitter;
    }


    @KafkaListener(topics = "kafkaTest2", groupId = "sections")
    public void listen(RequestKafkaTest result) {
        System.out.println("넘어왔당" + result.getMessage());
        SseEmitter emitter = this.emitters.get(result.getUserId());
        if (emitter != null) {
            try {
                emitter.send(result);
                System.out.println("여기도 통과");
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }
    }
}

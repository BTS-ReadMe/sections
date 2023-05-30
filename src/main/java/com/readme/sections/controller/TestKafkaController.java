package com.readme.sections.controller;

import com.readme.sections.service.TestKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class TestKafkaController {

    private final TestKafkaService testKafkaService;

    @GetMapping("/kafkaTest")
    public SseEmitter getPaymentResult(@RequestParam String userId) {
        return testKafkaService.subscribe(userId);
    }
}

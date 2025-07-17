package com.apiVerse.UserCase.controller;

import com.apiVerse.UserCase.kafka.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaController(KafkaProducer kafkaProducer, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/kafka/send")
    public String send(@RequestBody String message) {
        String event = String.format("{\"eventType\":\"UserRegistered\", \"userName\":\"%s\"}", message);
        kafkaTemplate.send("users", event);
        return "Success";
    }
}

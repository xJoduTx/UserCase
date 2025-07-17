package com.example.demo.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

//    private final RateLimiter rateLimiter = RateLimiter.create(2);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "users", groupId = "my_consumer", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message){
//        rateLimiter.acquire();
        JsonNode event = null;
        try {
            event = objectMapper.readTree(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if ("UserRegistered".equals(event.get("eventType").asText())) {
            System.out.println("Received message = " + message);
        }
    }
}

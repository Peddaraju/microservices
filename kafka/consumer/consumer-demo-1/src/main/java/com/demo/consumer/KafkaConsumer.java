package com.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${spring.demo.kafka.topic1}", groupId ="${spring.demo.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message){
        LOGGER.info(String.format("Message received -> %s", message));
    }

}

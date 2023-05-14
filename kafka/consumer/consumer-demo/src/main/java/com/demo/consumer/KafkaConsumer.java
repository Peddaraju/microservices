package com.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "topicraj_1", groupId ="raju_demo_group_id")
    public void consume(String message){
        LOGGER.info(String.format("Message received -> %s", message));
    }
}

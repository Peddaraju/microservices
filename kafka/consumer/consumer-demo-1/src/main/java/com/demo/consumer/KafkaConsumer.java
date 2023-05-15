package com.demo.consumer;

import com.demo.beans.Greeting;
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

    @KafkaListener(topics = "${spring.demo.kafka.topic2}", groupId ="${spring.demo.kafka.groupId}", containerFactory = "greetingKafkaListenerContainerFactory")
    public void consumeGreeting(Greeting greeting){
        LOGGER.info(String.format("Greeting received -> %s", greeting.getName()));
    }

}

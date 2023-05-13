package com.demo.producer;

import com.demo.beans.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${demo.kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Greeting> greetingkafkaTemplate;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, UUID.randomUUID().toString(), message);
        future.whenComplete(((result, throwable) -> {
            if (throwable == null) {
                LOG.info("Sent message=[" + result.getProducerRecord().value() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                LOG.info("Unable to send message=[" + result.getProducerRecord().value() + "] due to : " + throwable.getMessage());
            }
        }));
    }

    public void sendGreeting(Greeting greeting) {
        CompletableFuture<SendResult<String, Greeting>> future = greetingkafkaTemplate.send(topic,  UUID.randomUUID().toString(), greeting);
        future.whenComplete(((result, throwable) -> {
            if (throwable == null) {
                LOG.info("Sent message=[" + result.getProducerRecord().value() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                LOG.info("Unable to send message=[" + result.getProducerRecord().value() + "] due to : " + throwable.getMessage());
            }
        }));
    }

}

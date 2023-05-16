package com.demo.producer;

import com.demo.beans.Greeting;
import demo.kafka.event.PaymentSent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
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

    @Value("${demo.kafka.avro.topic}")
    private String avroTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Greeting> greetingkafkaTemplate;

    @Autowired
    private KafkaTemplate<String, PaymentSent> paymentSentkafkaTemplate;

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

    public SendResult sendMessage(String key, PaymentSent paymentSent) {
        try {
            final ProducerRecord<String, PaymentSent> record = new ProducerRecord<>(avroTopic, key, paymentSent);
            final SendResult result = (SendResult) paymentSentkafkaTemplate.send(record).get();
            final RecordMetadata metadata = result.getRecordMetadata();
            LOG.info(String.format("Sent record(key=%s value=%s) meta(topic=%s, partition=%d, offset=%d)", record.key(), record.value(), metadata.topic(), metadata.partition(), metadata.offset()));
            return result;
        } catch (Exception e) {
            String message = "Error sending message to topic " + avroTopic;
            LOG.error(message);
            throw new RuntimeException(message, e);
        }
    }

}

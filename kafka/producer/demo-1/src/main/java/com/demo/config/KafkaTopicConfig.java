package com.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.demo.kafka.bootstrap.servers}")
    private String bootstrapAddress;

    @Value("${demo.kafka.topic}")
    private String topic;

    @Value("${demo.kafka.avro.topic}")
    private String avroTopic;

    @Value("${spring.demo.kafka.sasl.mechanism}")
    private String saslMechanism;

    @Value("${spring.demo.kafka.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value("${spring.demo.kafka.security.protocol}")
    private String securityProtocol;

    @Autowired
    private Config configProps;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(configProps.configProps());
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic(topic, 3, (short) 3);
    }

    @Bean
    public NewTopic avroTopic() {
        return new NewTopic(avroTopic, 3, (short)3);
    }
}

package com.demo.consumer.config;

import com.demo.beans.Greeting;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value(value = "${spring.demo.kafka.bootstrap.servers}")
    private String bootstrapAddress;

    @Value("${spring.demo.kafka.groupId}")
    private String groupId;

    @Value("${spring.demo.kafka.sasl.mechanism}")
    private String saslMechanism;

    @Value("${spring.demo.kafka.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value("${spring.demo.kafka.security.protocol}")
    private String securityProtocol;


    private Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put("sasl.mechanism", saslMechanism);
        props.put("sasl.jaas.config", saslJaasConfig);
        props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }


    private ConsumerFactory<String, String> consumerFactory(StringDeserializer keyDeserializer, StringDeserializer valueDeserializer) {
        return new DefaultKafkaConsumerFactory<>(getProperties(), keyDeserializer, valueDeserializer);
    }

    private ConsumerFactory<String, Greeting> consumerFactory(StringDeserializer keyDeserializer, JsonDeserializer valueDeserializer) {
        return new DefaultKafkaConsumerFactory<>(getProperties(), keyDeserializer, valueDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(new StringDeserializer(), new StringDeserializer()));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Greeting> greetingKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Greeting> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(new StringDeserializer(), new JsonDeserializer(Greeting.class)));
        return factory;
    }
}

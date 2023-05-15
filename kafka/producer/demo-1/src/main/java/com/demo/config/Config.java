package com.demo.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    @Value(value = "${spring.demo.kafka.bootstrap.servers}")
    private String bootstrapAddress;

    @Value("${spring.demo.kafka.sasl.mechanism}")
    private String saslMechanism;

    @Value("${spring.demo.kafka.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value("${spring.demo.kafka.security.protocol}")
    private String securityProtocol;

    public Map<String, Object> configProps() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put("sasl.mechanism", saslMechanism);
        configs.put("sasl.jaas.config", saslJaasConfig);
        configs.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        return configs;
    }
}

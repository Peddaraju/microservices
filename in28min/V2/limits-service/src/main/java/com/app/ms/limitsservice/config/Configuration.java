package com.app.ms.limitsservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@org.springframework.context.annotation.Configuration
@ConfigurationProperties("limits-service")
@Setter
@Getter
public class Configuration {

    private int minimum;
    private int maximum;
}

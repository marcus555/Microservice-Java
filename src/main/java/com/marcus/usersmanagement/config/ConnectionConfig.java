package com.marcus.usersmanagement.config;

import com.marcus.usersmanagement.util.RestTemplateErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ConnectionConfig {
    @Bean
    public RestTemplate restTemplateGetOsAmoung(RestTemplateBuilder restTemplateBuilder, Environment env) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(Integer.parseInt(
                        env.getProperty("application.client.endpoint.method.timeout.connection"))))
                .setReadTimeout(Duration.ofMillis(Integer
                        .parseInt(env.getProperty("application.client.endpoint.method.timeout.read"))))
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }
}

package com.bootcamp.accounttransactions.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${config.base.endpoint}")
    private String url;

    @Bean
    public WebClient getWebClient() {
        return WebClient.create(url);
    }
}
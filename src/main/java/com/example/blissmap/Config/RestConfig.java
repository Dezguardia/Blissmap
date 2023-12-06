package com.example.blissmap.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
// Configuration du bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
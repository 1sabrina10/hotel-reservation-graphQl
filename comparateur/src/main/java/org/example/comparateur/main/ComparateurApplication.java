package org.example.comparateur.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(scanBasePackages = {
        "org.example.comparateur.controller",
        "org.example.comparateur.service"
})
@EnableJpaRepositories(basePackages = "org.example.comparateur.repository")
@EntityScan(basePackages = "org.example.comparateur.model")
public class ComparateurApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComparateurApplication.class, args);
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
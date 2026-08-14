package org.example.agence.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "org.example.agence")
@EntityScan(basePackages = "org.example.agence.model")

@EnableJpaRepositories(basePackages = "org.example.agence.repository")
public class AgenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgenceApplication.class, args);
    }
}

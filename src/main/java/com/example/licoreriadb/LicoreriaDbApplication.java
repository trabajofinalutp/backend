package com.example.licoreriadb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.licoreriadb.Repository")
@EntityScan(basePackages = "com.example.licoreriadb.Model")
public class LicoreriaDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicoreriaDbApplication.class, args);
    }

}

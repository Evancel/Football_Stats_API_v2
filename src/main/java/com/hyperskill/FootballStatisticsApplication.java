package com.hyperskill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FootballStatisticsApplication {

    public static void main(String[] args) {
        // Start Spring Boot application
        ConfigurableApplicationContext context = SpringApplication.run(FootballStatisticsApplication.class, args);
        System.out.println("Football Statistics Application started successfully!");
    }
}

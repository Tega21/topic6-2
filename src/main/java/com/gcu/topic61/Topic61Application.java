package com.gcu.topic61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gcu", "com.gcu.topic61"})  // Scan BOTH packages
@EnableMongoRepositories(basePackages = "com.gcu.data.repository")
public class Topic61Application {
    public static void main(String[] args) {
        SpringApplication.run(Topic61Application.class, args);
    }
}
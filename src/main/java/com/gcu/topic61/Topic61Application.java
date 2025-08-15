package com.gcu.topic61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.gcu")
@EnableMongoRepositories(basePackages = "com.gcu")
public class Topic61Application {

    public static void main(String[] args) {
        // Temporary: only use this to generate a hash you paste into Atlas,
        // then remove or comment it out so you don't keep printing new hashes.
        String encoded = new BCryptPasswordEncoder().encode("test");
        System.out.println("Encoded password: " + encoded);

        SpringApplication.run(Topic61Application.class, args);
    }
}

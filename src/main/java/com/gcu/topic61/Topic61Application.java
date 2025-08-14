package com.gcu.topic61;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Topic61Application {

    public static void main(String[] args) {
        // Temporary - remove after getting the encoded password
        String encoded = new BCryptPasswordEncoder().encode("test");
        System.out.println("Encoded password: " + encoded);

        SpringApplication.run(Topic61Application.class, args);
    }
}
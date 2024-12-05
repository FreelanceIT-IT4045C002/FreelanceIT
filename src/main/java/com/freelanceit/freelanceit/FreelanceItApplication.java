package com.freelanceit.freelanceit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The entry point of the Freelance IT application.
 *
 * This class contains the main method that starts the Spring Boot application.
 */
@SpringBootApplication
@EnableCaching
public class FreelanceItApplication {

    /**
     * The main method that serves as the entry point for the application.
     *
     * @param args command-line arguments passed during application startup
     */
    public static void main(String[] args) {
        SpringApplication.run(FreelanceItApplication.class, args);
    }

}

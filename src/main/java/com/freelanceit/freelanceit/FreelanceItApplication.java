package com.freelanceit.freelanceit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the FreelanceIt application.
 * This class is annotated with @SpringBootApplication, which combines
 * @Configuration, @EnableAutoConfiguration, and @ComponentScan annotations.
 */
@SpringBootApplication
public class FreelanceItApplication {

    /**
     * The main method which serves as the entry point for the application.
     * It uses SpringApplication.run() to bootstrap and launch the application.
     *
     * @param args command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(FreelanceItApplication.class, args);
    }

}

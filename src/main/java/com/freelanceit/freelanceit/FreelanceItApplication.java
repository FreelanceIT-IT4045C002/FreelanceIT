package com.freelanceit.freelanceit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FreelanceItApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreelanceItApplication.class, args);
    }

}

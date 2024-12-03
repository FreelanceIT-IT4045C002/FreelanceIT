package com.freelanceit.freelanceit.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for login credentials.
 * This class encapsulates the username and password used for authentication.
 * It uses Lombok annotations to automatically generate getter and setter methods.
 */
@Getter
@Setter
public class LoginDTO {

    /**
     * The username for authentication.
     */
    private String username;

    /**
     * The password for authentication.
     */
    private String password;
}

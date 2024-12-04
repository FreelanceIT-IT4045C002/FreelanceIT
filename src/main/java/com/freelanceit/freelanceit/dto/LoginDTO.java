package com.freelanceit.freelanceit.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for user login information.
 *
 * This class encapsulates the username and password fields
 * required for user authentication.
 */
@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;
}

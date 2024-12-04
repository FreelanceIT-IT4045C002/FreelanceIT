package com.freelanceit.freelanceit.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entity class representing a User in the system.
 *
 * This class implements the UserDetails interface from Spring Security,
 * allowing it to be used for authentication and authorization purposes.
 */
@Entity
@Getter
public class User implements UserDetails {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The username of the user.
     * This field must be unique.
     */
    @Setter
    @Column(unique = true)
    private String username;

    /**
     * The password of the user.
     */
    @Setter
    private String password;

    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection of GrantedAuthority objects representing
     *         the authorities granted to the user. This implementation
     *         returns null, indicating no specific authorities are assigned.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password as a String
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username as a String
     */
    @Override
    public String getUsername() {
        return this.username;
    }
}


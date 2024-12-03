package com.freelanceit.freelanceit.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Represents a User entity in the system.
 * This class implements UserDetails for Spring Security integration.
 * It uses JPA annotations for database mapping and Lombok for getter generation.
 */
@Entity
@Getter
public class User implements UserDetails {

    /**
     * The unique identifier for the user.
     * This field is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The username of the user. Must be unique.
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
     * Returns the authorities granted to the user. This method is required by UserDetails.
     * In this implementation, it returns null, indicating no specific authorities.
     *
     * @return null, as no specific authorities are defined
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return the user's username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    // Note: The following methods are part of UserDetails interface but are not implemented.
    // They should be implemented or annotated with @Override if specific behavior is needed.
    // isAccountNonExpired()
    // isAccountNonLocked()
    // isCredentialsNonExpired()
    // isEnabled()
}


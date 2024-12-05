package com.freelanceit.freelanceit.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for setting up web security in the application.
 * This class configures the security settings, including authentication, authorization,
 * password encoding, and login/logout handling using Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the HTTP security settings for the application.
     * This method disables CSRF protection, configures URL patterns for permitted access,
     * sets up form-based login, and configures logout handling.
     *
     * @param http The HttpSecurity object used to configure security settings.
     * @return The configured SecurityFilterChain.
     * @throws Exception If any error occurs while configuring the HTTP security settings.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disables CSRF protection
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Permits access to static resources
                        .requestMatchers("/api/login", "/api/register", "/login", "/register").permitAll() // Permits access to login/register endpoints
                        .anyRequest().authenticated() // Requires authentication for any other request
                )
                .formLogin(form -> form
                        .loginPage("/login") // Specifies the login page URL
                        .loginProcessingUrl("/api/login") // Specifies the login processing URL
                        .permitAll() // Allows unrestricted access to the login page
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout") // Specifies the logout URL
                        .logoutSuccessUrl("/login") // Redirects to the login page after successful logout
                        .permitAll() // Allows unrestricted access to the logout functionality
                );

        return http.build(); // Builds and returns the SecurityFilterChain
    }

    /**
     * Provides a PasswordEncoder bean that uses BCrypt hashing for password encoding.
     *
     * @return The PasswordEncoder implementation (BCryptPasswordEncoder).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Returns an instance of BCryptPasswordEncoder for encoding passwords
    }

    /**
     * Provides an AuthenticationManager bean used for handling authentication processes.
     *
     * @param authConfig The AuthenticationConfiguration object used to retrieve the AuthenticationManager.
     * @return The configured AuthenticationManager.
     * @throws Exception If any error occurs while retrieving the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager(); // Returns the AuthenticationManager from the provided configuration
    }
}

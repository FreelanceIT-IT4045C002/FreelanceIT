package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dao.UserRepository;
import com.freelanceit.freelanceit.dto.LoginDTO;
import com.freelanceit.freelanceit.dto.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user-related operations, including loading users by username
 * and registering new users. This class implements Spring Security's UserDetailsService interface
 * to integrate with the authentication mechanism.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a UserService with the given UserRepository and PasswordEncoder.
     *
     * @param userRepository The repository used to fetch and save user data.
     * @param passwordEncoder The password encoder used to encode user passwords.
     */
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Loads a user by their username from the user repository.
     *
     * @param username The username of the user to be loaded.
     * @return The UserDetails object corresponding to the provided username.
     * @throws UsernameNotFoundException If no user with the provided username is found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    /**
     * Registers a new user by saving their username and encoded password to the repository.
     *
     * @param loginDTO The data transfer object containing the username and password of the user to be registered.
     * @return The newly created and saved User object.
     * @throws RuntimeException If the username is already taken by an existing user.
     */
    public User registerUser(LoginDTO loginDTO) {
        if (userRepository.findByUsername(loginDTO.getUsername()) != null) {
            throw new RuntimeException("Username is already taken");
        }

        User newUser = new User();
        newUser.setUsername(loginDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(loginDTO.getPassword()));

        return userRepository.save(newUser);
    }
}

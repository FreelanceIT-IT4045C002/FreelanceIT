package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dao.UserRepository;
import com.freelanceit.freelanceit.dto.LoginDTO;
import com.freelanceit.freelanceit.dto.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

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

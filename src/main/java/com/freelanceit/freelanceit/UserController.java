package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.LoginDTO;
import com.freelanceit.freelanceit.dto.User;
import com.freelanceit.freelanceit.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class for handling user-related operations such as registration, login, and logout.
 * This controller is mapped to the "/api" endpoint.
 */
@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    /**
     * Constructor for UserController.
     *
     * @param userService the service for user-related operations
     * @param authenticationManager the authentication manager for handling user authentication
     */
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Handles user registration requests.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @param model the Spring MVC Model object
     * @return a redirect string to the login page on success or back to the registration page on error
     */
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUsername(username);
            loginDTO.setPassword(password);
            User registeredUser = userService.registerUser(loginDTO);
            return "redirect:/login?registered";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "redirect:/register?error";
        }
    }

    /**
     * Handles user login requests.
     *
     * @param loginDTO the Data Transfer Object containing login credentials
     * @return a redirect string to the index page on successful login or back to the login page on error
     */
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/index";
        } catch (AuthenticationException e) {
            return "redirect:/login?error";
        }
    }

    /**
     * Handles user logout requests.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @return a ResponseEntity with a success message
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("Logout successful");
    }
}

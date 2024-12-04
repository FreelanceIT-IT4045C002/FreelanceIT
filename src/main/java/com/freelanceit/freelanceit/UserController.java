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
 * Controller class responsible for handling user-related operations,
 * including registration, login, and logout functionalities.
 */
@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    /**
     * Constructor for UserController.
     *
     * @param userService the service responsible for user operations
     * @param authenticationManager the manager for handling authentication
     */
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Handles POST requests to register a new user.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @param model the model object used to pass data to the view
     * @return a redirect URL based on whether registration was successful or not
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
     * Handles POST requests to log in a user.
     *
     * @param loginDTO the LoginDTO object containing username and password
     * @return a redirect URL based on whether login was successful or not
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
     * Handles POST requests to log out a user.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @return a ResponseEntity indicating logout success
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

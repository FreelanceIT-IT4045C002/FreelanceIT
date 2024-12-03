package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.LoginDTO;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


/**
 * Controller class handling basic navigation and authentication-related views.
 * This controller manages the home page, login page, and registration page.
 */
@Controller
public class HomeController {

    /**
     * Handles requests to the home page.
     *
     * @return the name of the view to render ("index")
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Handles requests to the login page.
     * Adds a LoginDTO object to the model and handles registration success and login error messages.
     *
     * @param registered a string parameter indicating if registration was successful
     * @param error a string parameter indicating if there was a login error
     * @param model the Spring MVC Model object
     * @return the name of the view to render ("login")
     */
    @GetMapping("/login")
    public String login(String registered, String error, Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("login", loginDTO);
        if (registered != null) {
            model.addAttribute("message", "Registration successful. Please log in.");
        }
        if (error != null) {
            model.addAttribute("message", "There was a problem logging in, try again.");
        }
        return "login";
    }

    /**
     * Handles requests to the registration page.
     * Adds a LoginDTO object to the model for registration and handles registration error messages.
     *
     * @param error a string parameter indicating if there was a registration error
     * @param model the Spring MVC Model object
     * @return the name of the view to render ("register")
     */
    @GetMapping("/register")
    public String register(String error, Model model) {
        LoginDTO registerDTO = new LoginDTO();
        model.addAttribute("register", registerDTO);
        if (error != null) {
            model.addAttribute("message", "There was a problem creating a new user.");
        }
        return "register";
    }
}


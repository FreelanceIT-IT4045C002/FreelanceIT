package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.LoginDTO;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

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


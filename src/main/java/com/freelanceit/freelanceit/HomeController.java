package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.LoginDTO;
import com.freelanceit.freelanceit.dto.Project;
import com.freelanceit.freelanceit.service.IProjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.Collection;


@Controller
public class HomeController {
    @Autowired
    IProjectService projectService;

    @GetMapping("/")
    public String index(Model model) {
        Collection<Project> projectList = projectService.fetchAll();
        model.addAttribute("projectList", projectList);
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


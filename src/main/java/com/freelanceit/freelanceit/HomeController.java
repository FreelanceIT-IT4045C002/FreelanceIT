package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.LoginDTO;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
public class HomeController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);



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

    @GetMapping("/add/todo")
    public String addTodo() {
        return "addTask";
    }

    @GetMapping("/inprogress")
    public String inProgress() {
        return "inprogress";
    }

    @DeleteMapping("/delete/task")
    public ResponseEntity<String> deleteTask(@RequestParam("id") int id) {
        try {
            // Simulate finding and deleting the task
            logger.info("Deleting task with ID: {}");
            // Logic to check if the task exists and delete it
            return ResponseEntity.ok("Task deleted successfully!");
        } catch (Exception e) {
            logger.error("Error deleting task with ID: {}", id, e);
            return ResponseEntity.status(500).body("Failed to delete task");
        }
    }
}


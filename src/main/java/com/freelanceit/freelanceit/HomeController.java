package com.freelanceit.freelanceit;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.freelanceit.freelanceit.dto.LoginDTO;
import com.freelanceit.freelanceit.dto.Project;
import com.freelanceit.freelanceit.dto.User;
import com.freelanceit.freelanceit.service.IProjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import dto.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import service.IProjectService;
import service.ProjectService;


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

//    @GetMapping("/add/task")
//    public String addTask() {
//        return "taskpage"; // Ensure that this matches the HTML filename, e.g., taskpage.html
//    }

    @GetMapping("/add/todo")
    public String addTodo() {
        return "addtodoList";
    }

    @GetMapping("/inprogress")
    public String inProgress() {
        return "inprogress";
    }

    @PostMapping("/add/todo")
    public String submitTask(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("assigned") String assigned,
            Model model) {

        // Here you would typically save the task data to a database or process it
        System.out.println("Task Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Assigned to: " + assigned);

        // Add attributes to the model to pass back to the view if needed
        model.addAttribute("message", "Task added successfully!");

        // Redirect or forward to a confirmation page or back to the task form
        return "taskpage";
        // Ensure this points to the task confirmation or main task page
    }

//
//    @PostMapping(value="/add/project", consumes ="application/json", produces ="application/json" )
//    public Project createProject(@RequestBody Project project){
//        return project;
//    }
    @DeleteMapping("/delete/task")
    public ResponseEntity<String> deleteTask(@RequestParam("id") int id) {
        try {
            if (id < 0) {
                return ResponseEntity.badRequest().body("Invalid task ID. The ID must be non-negative.");
            }
            else {
                // Here, you would add logic to delete the task from your database or storage
                System.out.println("Deleting task with ID: " + id);
            };
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Deletion Falied. See console for details.");
        }
        // Simulate successful deletion response
        return ResponseEntity.ok("Task deleted successfully!");
    }
}


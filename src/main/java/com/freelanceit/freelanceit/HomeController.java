package com.freelanceit.freelanceit;

import java.util.logging.Level;
import java.util.logging.Logger;

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
    public String login() {
        return "login";
    }

    @GetMapping("/add/project")
    public String getProject() {
        return "project";
    }

    @GetMapping("/add/task")
    public String addTask() {
        return "taskpage"; // Ensure that this matches the HTML filename, e.g., taskpage.html
    }

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


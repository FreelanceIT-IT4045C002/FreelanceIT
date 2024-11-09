package com.freelanceit.freelanceit;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/add/project")
    public String getProject()
    {
        return "project";
    }

    @GetMapping("/add/task")
    public String addTask()
    {
        return "Taskpage"; // Ensure that this matches the HTML filename, e.g., Taskpage.html
    }

    @GetMapping("/add/todo")
    public String addTodo()
    {
        return "AddtodoList";
    }

    @GetMapping("/inprogress")
    public String inProgress()
    {
        return "inprogress";
    }

    @PostMapping("/add/todo")
    public String submitTask(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("assigned") String assigned,
            Model model) {

        if (title == null || title.isEmpty()) {
            model.addAttribute("error", "Title is required.");
            return "Taskpage";
        }
        if (description == null || description.isEmpty()) {
            model.addAttribute("error", "Description is required.");
            return "Taskpage";
        }
        if (assigned == null || assigned.isEmpty()) {
            model.addAttribute("error", "Assigned person is required.");
            return "Taskpage";
        }

        // Here you would typically save the task data to a database or process it
        System.out.println("Task Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Assigned to: " + assigned);

        // Add attributes to the model to pass back to the view if needed
        model.addAttribute("message", "Task added successfully!");

        // Redirect or forward to a confirmation page or back to the task form
        return "Taskpage"; // Ensure this points to the task confirmation or main task page
    }

    @DeleteMapping("/delete/task")
    public ResponseEntity<String> deleteTask(@RequestParam("id") int id) {
        // Here, you would add logic to delete the task from your database or storage
        System.out.println("Deleting task with ID: " + id);

        // Simulate successful deletion response
        return ResponseEntity.ok("Task deleted successfully!");
    }
}

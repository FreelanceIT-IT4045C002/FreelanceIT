package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.Task;
import com.freelanceit.freelanceit.dto.Project;
import com.freelanceit.freelanceit.service.IProjectService;
import com.freelanceit.freelanceit.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
    @Autowired
    ITaskService taskService;

    @Autowired
    IProjectService projectService;

    @GetMapping("/add/task")
    public String getTask(Model model) {
        Task task = new Task();
        model.addAttribute(task);
        return "task";
    }

    @PostMapping("/CreateTask/{id}")
    public String createTask(Task task, @PathVariable("id") int projectId) {
        try {
            Project project = projectService.fetchById(projectId);
            task.setProject(project);
            taskService.save(task);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/add/task?error";
        }
        return "index";
    }
}


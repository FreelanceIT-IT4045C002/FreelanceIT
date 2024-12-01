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

    @GetMapping("/project/{projectId}/tasks/add")
    public String getTask(Model model, @PathVariable int projectId) {
        Task task = new Task();
        model.addAttribute(task);
        model.addAttribute(projectId);
        return "addtodoList";
    }

    @PostMapping("/CreateTask/{projectId}")
    public String createTask(Task task, @PathVariable int projectId) {
        try {
            task.setProject(projectService.fetchById(projectId));
            taskService.save(task);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/project/" + projectId + "/tasks/add?error";
        }
        return "index";
    }
}


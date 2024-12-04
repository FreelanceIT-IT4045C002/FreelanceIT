package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.Task;
import com.freelanceit.freelanceit.service.IProjectService;
import com.freelanceit.freelanceit.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    ITaskService taskService;

    @Autowired
    IProjectService projectService;

    @GetMapping("/project/{projectId}/tasks")
    public String getTask(Model model, @PathVariable int projectId) {
        List<Task> taskList = taskService.fetchAllByProjectId(projectId);
        model.addAttribute("tasks", taskList);
        return "taskListPage";
    }

    @GetMapping("/project/{projectId}/tasks/add")
    public String getTask(Model model, String error,@PathVariable int projectId) {
        Task task = new Task();
        model.addAttribute(task);
        model.addAttribute(projectId);
        if(error != null) {
            model.addAttribute("message", "There was an error while adding a task, please try again");
        }
        return "addTask";
    }

    @PostMapping("/api/project/{projectId}/tasks/add")
    public String createTask(Task task, @PathVariable int projectId) {
        try {
            task.setProject(projectService.fetchById(projectId));
            taskService.save(task);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/project/%d/tasks/add?error".formatted(projectId);
        }
        return "redirect:/project/%d/tasks".formatted(projectId);
    }

    @PostMapping("/api/project/{projectId}/tasks/{taskId}/delete")
    public String deleteTask(Model model, @PathVariable int projectId, @PathVariable int taskId) {
        try{
            taskService.delete(taskId);
        }
        catch(Exception e){
            e.printStackTrace();
            model.addAttribute("message", "There was a problem while deleting a task, please try again");
        }
        return "redirect:/project/%d/tasks".formatted(projectId);
    }
}


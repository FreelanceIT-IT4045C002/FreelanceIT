package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.Task;
import com.freelanceit.freelanceit.service.IProjectService;
import com.freelanceit.freelanceit.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Controller class for handling task-related operations within projects.
 * This controller manages the creation, display, and listing of tasks associated with projects.
 */
@Controller
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IProjectService projectService;

    /**
     * Handles GET requests for listing tasks of a specific project.
     *
     * @param model the Spring MVC Model object
     * @param projectId the ID of the project whose tasks are to be listed
     * @return the name of the view to render ("taskListPage")
     */
    @GetMapping("/project/{projectId}/tasks")
    public String getTask(Model model, @PathVariable int projectId) {
        List<Task> taskList = taskService.fetchAllByProjectId(projectId);
        model.addAttribute("tasks", taskList);
        return "taskListPage";
    }

    /**
     * Handles GET requests for the task creation page within a specific project.
     *
     * @param model the Spring MVC Model object
     * @param error a string parameter indicating if there was an error in task creation
     * @param projectId the ID of the project to which the task will be added
     * @return the name of the view to render ("addTask")
     */
    @GetMapping("/project/{projectId}/tasks/add")
    public String getTask(Model model, String error, @PathVariable int projectId) {
        Task task = new Task();
        model.addAttribute(task);
        model.addAttribute(projectId);
        if(error != null) {
            model.addAttribute("message", "There was an error while adding a task, please try again");
        }
        return "addTask";
    }

    /**
     * Handles POST requests for task creation within a specific project.
     *
     * @param task the Task object to be created, populated from form data
     * @param projectId the ID of the project to which the task will be added
     * @return a redirect string, either to the task list page on success or back to the add task page on error
     */
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
}


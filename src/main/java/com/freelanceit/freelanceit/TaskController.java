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

/**
 * Controller class responsible for handling requests related to task management
 * within a project.
 *
 * This class provides endpoints for viewing tasks, adding new tasks,
 * and deleting tasks associated with a specific project.
 */
@Controller
public class TaskController {

    @Autowired
    ITaskService taskService;

    @Autowired
    IProjectService projectService;

    /**
     * Handles GET requests to view all tasks for a specific project.
     *
     * @param model the model object used to pass data to the view
     * @param projectId the unique identifier of the project for which tasks are fetched
     * @return the name of the view (taskListPage) to be rendered
     */
    @GetMapping("/project/{projectId}/tasks")
    public String getTask(Model model, @PathVariable int projectId) {
        List<Task> taskList = taskService.fetchAllByProjectId(projectId);
        model.addAttribute("tasks", taskList);
        return "taskListPage";
    }

    /**
     * Handles GET requests to display the form for adding a new task to a specific project.
     *
     * @param model the model object used to pass data to the view
     * @param error indicates if there was an error while adding a task
     * @param projectId the unique identifier of the project to which the task will be added
     * @return the name of the view (addTask) to be rendered
     */
    @GetMapping("/project/{projectId}/tasks/add")
    public String getTask(Model model, String error, @PathVariable int projectId) {
        Task task = new Task();
        model.addAttribute(task);
        model.addAttribute(projectId);
        if (error != null) {
            model.addAttribute("message", "There was an error while adding a task, please try again");
        }
        return "addTask";
    }

    /**
     * Handles POST requests to create a new task for a specific project.
     *
     * @param task the Task object containing details of the new task to be created
     * @param projectId the unique identifier of the project to which the task will be added
     * @return a redirect URL based on whether the task was successfully created or not
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

    /**
     * Handles POST requests to delete a specific task from a project.
     *
     * @param model the model object used to pass data to the view
     * @param projectId the unique identifier of the project from which the task will be deleted
     * @param taskId the unique identifier of the task to be deleted
     * @return a redirect URL back to the list of tasks for that project
     */
    @PostMapping("/api/project/{projectId}/tasks/{taskId}/delete")
    public String deleteTask(Model model, @PathVariable int projectId, @PathVariable int taskId) {
        try {
            taskService.delete(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "There was a problem while deleting a task, please try again");
        }
        return "redirect:/project/%d/tasks".formatted(projectId);
    }
}


package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.Project;
import com.freelanceit.freelanceit.dto.User;
import com.freelanceit.freelanceit.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller class responsible for handling requests related to project management.
 *
 * This class provides endpoints for adding new projects and processing project creation.
 */
@Controller
public class ProjectController {

    @Autowired
    IProjectService projectService;

    /**
     * Handles GET requests to the URL "/project/add".
     *
     * Prepares a new Project object and adds it to the model for the view.
     * If there is an error indicated, a message is added to the model.
     *
     * @param model the model object used to pass data to the view
     * @param error indicates if there was an error adding a project
     * @return the name of the view (addProject) to be rendered
     */
    @GetMapping("/project/add")
    public String getProject(Model model, String error) {
        Project project = new Project();
        model.addAttribute(project);
        if (error != null) {
            model.addAttribute("message", "There was a problem adding a project, try again.");
        }
        return "addProject";
    }

    /**
     * Handles POST requests to the URL "/api/project/add".
     *
     * Processes the creation of a new project. It retrieves the currently authenticated user,
     * associates them with the project, and attempts to save it using the project service.
     * If an exception occurs during saving, it redirects back to the add project page with an error.
     *
     * @param project the Project object containing details of the new project to be created
     * @return a redirect URL based on whether the project was successfully created or not
     */
    @PostMapping("/api/project/add")
    public String createProject(Project project) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            project.setUser(user);
            projectService.save(project);
        } catch (Exception e) {
            //   throw new RuntimeException(e);
            e.printStackTrace();
            return "redirect:/project/add?error";
        }
        return "redirect:/";
    }
}

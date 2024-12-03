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
 * Controller class for handling project-related operations.
 * This controller manages the creation and display of projects.
 */
@Controller
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    /**
     * Handles GET requests for the project creation page.
     * Adds a new Project object to the model and handles error messages.
     *
     * @param model the Spring MVC Model object
     * @param error a string parameter indicating if there was an error in project creation
     * @return the name of the view to render ("addProject")
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
     * Handles POST requests for project creation.
     * Associates the project with the currently authenticated user and saves it.
     *
     * @param project the Project object to be created, populated from form data
     * @return a redirect string, either to the home page on success or back to the add project page on error
     */
    @PostMapping("/api/project/add")
    public String createProject(Project project) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            project.setUser(user);
            projectService.save(project);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/project/add?error";
        }
        return "redirect:/";
    }
}

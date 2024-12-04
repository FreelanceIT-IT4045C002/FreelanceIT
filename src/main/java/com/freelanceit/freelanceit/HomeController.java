package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.LoginDTO;
import com.freelanceit.freelanceit.dto.Project;
import com.freelanceit.freelanceit.service.IProjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.Collection;


/**
 * Controller class responsible for handling requests related to the home page,
 * login, and registration functionalities.
 *
 * This class interacts with the project service to fetch project data and
 * provides methods to render the appropriate views.
 */
@Controller
public class HomeController {

    @Autowired
    IProjectService projectService;

    /**
     * Handles GET requests to the root URL ("/").
     *
     * Fetches all projects and adds them to the model, then returns
     * the name of the view to be rendered (index).
     *
     * @param model the model object used to pass data to the view
     * @return the name of the view (index) to be rendered
     */
    @GetMapping("/")
    public String index(Model model) {
        Collection<Project> projectList = projectService.fetchAll();
        model.addAttribute("projectList", projectList);
        return "index";
    }

    /**
     * Handles GET requests to the login URL ("/login").
     *
     * Prepares a LoginDTO object for the login form and adds it to the model.
     * It also adds messages based on registration or login errors if applicable.
     *
     * @param registered indicates if registration was successful
     * @param error indicates if there was a login error
     * @param model the model object used to pass data to the view
     * @return the name of the view (login) to be rendered
     */
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

    /**
     * Handles GET requests to the registration URL ("/register").
     *
     * Prepares a LoginDTO object for the registration form and adds it to
     * the model. It also adds an error message if there was a problem creating a new user.
     *
     * @param error indicates if there was a registration error
     * @param model the model object used to pass data to the view
     * @return the name of the view (register) to be rendered
     */
    @GetMapping("/register")
    public String register(String error, Model model) {
        LoginDTO registerDTO = new LoginDTO();
        model.addAttribute("register", registerDTO);
        if (error != null) {
            model.addAttribute("message", "There was a problem creating a new user.");
        }
        return "register";
    }
}


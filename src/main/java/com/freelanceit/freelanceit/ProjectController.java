package com.freelanceit.freelanceit;

import com.freelanceit.freelanceit.dto.Project;
import com.freelanceit.freelanceit.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    @Autowired
    IProjectService projectService;

    @GetMapping("/add/project")
    public String getProject(Model model) {
        Project project = new Project();
        model.addAttribute(project);
        return "project";
    }

    @PostMapping("/CreateProject")
    public String createProject(Project project) {
        try {
            projectService.save(project);
        } catch (Exception e) {
            //   throw new RuntimeException(e);
            e.printStackTrace();
            return "error";
        }
        return "index";
    }
}

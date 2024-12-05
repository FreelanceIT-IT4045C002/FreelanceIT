package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dto.Project;
import com.freelanceit.freelanceit.dao.IProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectDAO projectDAO;

    /**
     * Default constructor for ProjectService.
     * This constructor is used for dependency injection via Spring framework.
     */
    public ProjectService() {
    }

    /**
     * Constructor for ProjectService that allows manual injection of the IProjectDAO.
     *
     * @param projectDAO The IProjectDAO implementation to be used by the service.
     */
    public ProjectService(IProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    /**
     * Fetches a project by its unique identifier.
     *
     * @param id The unique identifier of the project to be fetched.
     * @return The project with the specified id, or null if no project is found.
     */
    @Override
    @Cacheable(value = "project", key = "'project_' + #id")
    public Project fetchById(int id) {
        return projectDAO.fetch(id);
    }

    /**
     * Fetches all projects.
     *
     * @return A list of all projects. If no projects are found, an empty list is returned.
     */
    @Override
    @Cacheable(value = "project", key = "'all_projects'")
    public List<Project> fetchAll() {
        return projectDAO.findAll();
    }

    /**
     * Fetches all projects associated with a specific user.
     *
     * @param userId The unique identifier of the user whose projects are to be fetched.
     * @return A list of projects associated with the user. If no projects are found, an empty list is returned.
     */
    @Override
    @Cacheable(value = "project", key = "'user_projects_' + #userId")
    public List<Project> fetchByUserId(int userId) {
        return projectDAO.findByUser_Id(userId);
    }

    /**
     * Deletes a project by its unique identifier.
     *
     * @param id The unique identifier of the project to be deleted.
     * @throws Exception If the deletion fails for any reason, such as the project not existing.
     */
    @Override
    @CacheEvict(value = "project", allEntries = true)
    public void delete(int id) throws Exception {
        projectDAO.delete(id);
    }

    /**
     * Saves a project, either by creating a new project or updating an existing one.
     *
     * @param project The project to be saved.
     * @return The saved project, which may have updated properties (e.g., an auto-generated ID).
     * @throws Exception If the save operation fails for any reason, such as invalid data.
     * @throws IllegalArgumentException If the provided project is null.
     */
    @Override
    @CacheEvict(value = "project", allEntries = true)
    public Project save(Project project) throws Exception {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
        return projectDAO.save(project);
    }
}

package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing Project entities.
 * Provides methods for performing CRUD (Create, Read, Update, Delete)
 * operations on Project repositories.
 */
@Repository
public class ProjectDAO implements IProjectDAO {
    /**
     * Spring Data JPA repository for Project entities.
     */
    @Autowired
    ProjectRepository projectRepository;

    /**
     * Saves a new project to the database.
     *
     * @param project The project to be saved
     * @return The saved project, including any generated identifiers
     * @throws Exception If an error occurs during the save operation
     */
    @Override
    public Project save(Project project) throws Exception {
        try {
            return projectRepository.save(project);
        }
        catch (Exception e) {
            throw new Exception("Error saving project: " + project, e);
        }
    }

    /**
     * Retrieves all projects from the database.
     *
     * @return An immutable list of all projects
     */
    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return List.copyOf(projects);
    }

    /**
     * Fetches a specific project by its unique identifier.
     *
     * @param id The unique identifier of the project
     * @return The project with the specified ID
     * @throws IllegalArgumentException If no project is found with the given ID
     */
    @Override
    public Project fetch(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + id + " not found"));
    }

    /**
     * Deletes a project from the database by its ID.
     *
     * @param id The unique identifier of the project to be deleted
     */
    @Override
    public void delete(int id) {
        projectRepository.deleteById(id);
    }
}
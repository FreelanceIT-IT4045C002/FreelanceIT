package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the IProjectDAO interface for managing Project entities.
 * This class interacts with the underlying data storage through the ProjectRepository.
 */
@Repository
public class ProjectDAO implements IProjectDAO {

    @Autowired
    ProjectRepository projectRepository;

    /**
     * Saves a given project to the database.
     *
     * @param project the project to be saved
     * @return the saved project, including any generated fields (e.g., ID)
     * @throws Exception if there is an error during saving
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
     * @return a list of all projects
     */
    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return List.copyOf(projects);
    }

    /**
     * Retrieves all projects from the database that are associated with a specific user.
     *
     * @param userId the unique identifier of the user
     * @return a list of all projects associated with the user
     */
    @Override
    public List<Project> findByUser_Id(int userId) {
        return projectRepository.findByUser_Id(userId);
    }


    /**
     * Fetches a project by its unique identifier.
     *
     * @param id the unique identifier of the project
     * @return the project with the specified id
     * @throws IllegalArgumentException if no project with the specified id is found
     */
    @Override
    public Project fetch(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + id + " not found"));
    }

    /**
     * Deletes a project by its unique identifier.
     *
     * @param id the unique identifier of the project to be deleted
     */
    @Override
    public void delete(int id) {
        projectRepository.deleteById(id);
    }
}
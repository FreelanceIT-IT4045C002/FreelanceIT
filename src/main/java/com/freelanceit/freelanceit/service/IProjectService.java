package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dto.Project;

import java.util.List;

/**
 * Interface for Project service that provides methods for managing projects.
 * This includes retrieving projects by various filters, deleting a project,
 * and saving a project.
 */
public interface IProjectService {

    /**
     * Fetches a project by its unique identifier.
     *
     * @param id The unique identifier of the project to be fetched.
     * @return The project with the specified id, or null if no project is found.
     */
    Project fetchById(int id);

    /**
     * Fetches all projects.
     *
     * @return A list of all projects. If no projects are found, an empty list is returned.
     */
    List<Project> fetchAll();

    /**
     * Fetches all projects associated with a specific user.
     *
     * @param userId The unique identifier of the user whose projects are to be fetched.
     * @return A list of projects associated with the user. If no projects are found, an empty list is returned.
     */
    List<Project> fetchByUserId(int userId);

    /**
     * Deletes a project by its unique identifier.
     *
     * @param id The unique identifier of the project to be deleted.
     * @throws Exception If the deletion fails for any reason, such as the project not existing.
     */
    void delete(int id) throws Exception;

    /**
     * Saves a project, either by creating a new project or updating an existing one.
     *
     * @param project The project to be saved.
     * @return The saved project, which may have updated properties (e.g., an auto-generated ID).
     * @throws Exception If the save operation fails for any reason, such as invalid data.
     */
    Project save(Project project) throws Exception;
}
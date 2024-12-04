package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Project;

import java.util.List;

/**
 * Interface for Data Access Object (DAO) that provides methods
 * for managing Project entities in a persistent storage.
 */
public interface IProjectDAO {

    /**
     * Saves a given project to the database.
     *
     * @param project the project to be saved
     * @return the saved project, including any generated fields (e.g., ID)
     * @throws Exception if there is an error during saving
     */
    Project save(Project project) throws Exception;

    /**
     * Retrieves all projects from the database.
     *
     * @return a list of all projects
     */
    List<Project> findAll();

    /**
     * Retrieves all projects of a specific user from the database.
     *
     * @param userId the unique identifier of the user
     * @return a list of all projects of the specified user
     */
    List<Project> findByUser_Id(int userId);

    /**
     * Fetches a project by its unique identifier.
     *
     * @param id the unique identifier of the project
     * @return the project with the specified id, or null if not found
     */
    Project fetch(int id);

    /**
     * Deletes a project by its unique identifier.
     *
     * @param id the unique identifier of the project to be deleted
     */
    void delete(int id);
}

package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Project;

import java.util.List;

public interface IProjectDAO {

    /**
     * Saves a project to the data store.
     *
     * @param project the Project object to be saved
     * @return the saved Project object, potentially with updated information (e.g., generated ID)
     * @throws Exception if there's an error during the save operation
     */
    Project save(Project project) throws Exception;

    /**
     * Retrieves all projects from the data store.
     *
     * @return a List of all Project objects in the data store
     */
    List<Project> findAll();

    /**
     * Fetches a specific project by its ID.
     *
     * @param id the unique identifier of the project to fetch
     * @return the Project object with the specified ID, or null if not found
     */
    Project fetch(int id);

    /**
     * Deletes a project from the data store based on its ID.
     *
     * @param id the unique identifier of the project to delete
     */
    void delete(int id);
}


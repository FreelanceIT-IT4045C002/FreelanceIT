package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Task;

import java.util.List;

/**
 * Interface for Data Access Object (DAO) that provides methods
 * for managing Task entities in a persistent storage.
 */
public interface ITaskDAO {

    /**
     * Saves a given task to the database.
     *
     * @param task the task to be saved
     * @return the saved task, including any generated fields (e.g., ID)
     * @throws Exception if there is an error during saving
     */
    Task save(Task task) throws Exception;

    /**
     * Retrieves all tasks from the database.
     *
     * @return a list of all tasks
     */
    List<Task> findAll();

    /**
     * Fetches all tasks associated with a specific project by its unique identifier.
     *
     * @param projectId the unique identifier of the project
     * @return a list of tasks associated with the specified project
     */
    List<Task> findAllByProjectId(int projectId); // Fetch tasks by project ID

    /**
     * Fetches a task by its unique identifier.
     *
     * @param id the unique identifier of the task
     * @return the task with the specified id, or null if not found
     */
    Task fetch(int id);

    /**
     * Deletes a task by its unique identifier.
     *
     * @param id the unique identifier of the task to be deleted
     */
    void delete(int id);
}
package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Task;

import java.util.List;

public interface ITaskDAO {

    /**
     * Saves a task to the data store.
     *
     * @param task the Task object to be saved
     * @return the saved Task object, potentially with updated information (e.g., generated ID)
     * @throws Exception if there's an error during the save operation
     */
    Task save(Task task) throws Exception;

    /**
     * Retrieves all tasks from the data store.
     *
     * @return a List of all Task objects in the data store
     */
    List<Task> findAll();

    /**
     * Retrieves all tasks associated with a specific project.
     *
     * @param projectId the unique identifier of the project
     * @return a List of Task objects associated with the specified project
     */
    List<Task> findAllByProjectId(int projectId);

    /**
     * Fetches a specific task by its ID.
     *
     * @param id the unique identifier of the task to fetch
     * @return the Task object with the specified ID, or null if not found
     */
    Task fetch(int id);

    /**
     * Deletes a task from the data store based on its ID.
     *
     * @param id the unique identifier of the task to delete
     */
    void delete(int id);
}

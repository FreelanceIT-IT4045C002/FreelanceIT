package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dto.Task;

import java.util.List;

/**
 * Interface for Task service that provides methods for managing tasks.
 * This includes retrieving tasks by various filters, deleting a task,
 * and saving a task.
 */
public interface ITaskService {

    /**
     * Fetches a task by its unique identifier.
     *
     * @param id The unique identifier of the task to be fetched.
     * @return The task with the specified id, or null if no task is found.
     */
    Task fetchById(int id);

    /**
     * Fetches all tasks.
     *
     * @return A list of all tasks. If no tasks are found, an empty list is returned.
     */
    List<Task> fetchAll();

    /**
     * Fetches all tasks associated with a specific project.
     *
     * @param projectId The unique identifier of the project whose tasks are to be fetched.
     * @return A list of tasks associated with the project. If no tasks are found, an empty list is returned.
     */
    List<Task> fetchAllByProjectId(int projectId);

    /**
     * Deletes a task by its unique identifier.
     *
     * @param id The unique identifier of the task to be deleted.
     * @throws Exception If the deletion fails for any reason, such as the task not existing.
     */
    void delete(int id) throws Exception;

    /**
     * Saves a task, either by creating a new task or updating an existing one.
     *
     * @param task The task to be saved.
     * @return The saved task, which may have updated properties (e.g., an auto-generated ID).
     * @throws Exception If the save operation fails for any reason, such as invalid data.
     */
    Task save(Task task) throws Exception;
}


package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dto.Task;
import com.freelanceit.freelanceit.dao.ITaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private ITaskDAO taskDAO;

    /**
     * Default constructor for TaskService.
     * This constructor is used for dependency injection via Spring framework.
     */
    public TaskService() {
    }

    /**
     * Constructor for TaskService that allows manual injection of the ITaskDAO.
     *
     * @param taskDAO The ITaskDAO implementation to be used by the service.
     */
    public TaskService(ITaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    /**
     * Fetches a task by its unique identifier.
     *
     * @param id The unique identifier of the task to be fetched.
     * @return The task with the specified id, or null if no task is found.
     */
    @Override
    @Cacheable(value = "task", key = "'task_' + #id")
    public Task fetchById(int id) {
        return taskDAO.fetch(id);
    }

    /**
     * Fetches all tasks.
     *
     * @return A list of all tasks. If no tasks are found, an empty list is returned.
     */
    @Override
    @Cacheable(value = "task", key = "'all_tasks'")
    public List<Task> fetchAll() {
        return taskDAO.findAll();
    }

    /**
     * Fetches all tasks associated with a specific project.
     *
     * @param projectId The unique identifier of the project whose tasks are to be fetched.
     * @return A list of tasks associated with the project. If no tasks are found, an empty list is returned.
     */
    @Override
    @Cacheable(value = "task", key = "'project_' + #projectId")
    public List<Task> fetchAllByProjectId(int projectId) {
        return taskDAO.findAllByProjectId(projectId);
    }

    /**
     * Deletes a task by its unique identifier.
     *
     * @param id The unique identifier of the task to be deleted.
     * @throws Exception If the deletion fails for any reason, such as the task not existing.
     */
    @Override
    @CacheEvict(value = "task", allEntries = true)
    public void delete(int id) throws Exception {
        taskDAO.delete(id);
    }

    /**
     * Saves a task, either by creating a new task or updating an existing one.
     *
     * @param task The task to be saved.
     * @return The saved task, which may have updated properties (e.g., an auto-generated ID).
     * @throws Exception If the save operation fails for any reason, such as invalid data.
     * @throws IllegalArgumentException If the provided task is null.
     */
    @Override
    @CacheEvict(value = "task", allEntries = true)
    public Task save(Task task) throws Exception {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        return taskDAO.save(task);
    }
}


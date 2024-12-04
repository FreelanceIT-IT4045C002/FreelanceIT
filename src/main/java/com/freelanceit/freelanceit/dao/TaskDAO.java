package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ITaskDAO interface for managing Task entities.
 * This class interacts with the underlying data storage through the TaskRepository.
 */
@Repository
public class TaskDAO implements ITaskDAO {

    @Autowired
    TaskRepository taskRepository;

    /**
     * Saves a given task to the database.
     *
     * @param task the task to be saved
     * @return the saved task, including any generated fields (e.g., ID)
     * @throws Exception if there is an error during saving
     */
    @Override
    public Task save(Task task) throws Exception {
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new Exception("Error saving task: " + task, e);
        }
    }

    /**
     * Retrieves all tasks from the database.
     *
     * @return a list of all tasks
     */
    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return List.copyOf(tasks);
    }

    /**
     * Retrieves all tasks associated with a specific project by its unique identifier.
     *
     * @param projectId the unique identifier of the project
     * @return a list of tasks associated with the specified project
     */
    public List<Task> findAllByProjectId(int projectId) {
        return taskRepository.findByProject_ProjectId(projectId);
    }

    /**
     * Fetches a task by its unique identifier.
     *
     * @param id the unique identifier of the task
     * @return the task with the specified id
     * @throws IllegalArgumentException if no task with the specified id is found
     */
    @Override
    public Task fetch(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found"));
    }

    /**
     * Deletes a task by its unique identifier.
     *
     * @param id the unique identifier of the task to be deleted
     */
    @Override
    public void delete(int id) {
        taskRepository.deleteById(id);
    }
}


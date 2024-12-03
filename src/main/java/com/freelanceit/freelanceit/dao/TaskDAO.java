package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDAO implements ITaskDAO {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Saves a task to the database.
     *
     * @param task the Task object to be saved
     * @return the saved Task object
     * @throws Exception if there's an error during the save operation
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
     * @return an unmodifiable List of all Task objects in the database
     */
    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return List.copyOf(tasks);
    }

    /**
     * Retrieves all tasks associated with a specific project.
     *
     * @param projectId the ID of the project
     * @return a List of Task objects associated with the specified project
     */
    @Override
    public List<Task> findAllByProjectId(int projectId) {
        return taskRepository.findByProject_ProjectId(projectId);
    }

    /**
     * Fetches a specific task by its ID.
     *
     * @param id the ID of the task to fetch
     * @return the Task object with the specified ID
     * @throws IllegalArgumentException if no task is found with the given ID
     */
    @Override
    public Task fetch(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found"));
    }

    /**
     * Deletes a task from the database based on its ID.
     *
     * @param id the ID of the task to delete
     */
    @Override
    public void delete(int id) {
        taskRepository.deleteById(id);
    }
}


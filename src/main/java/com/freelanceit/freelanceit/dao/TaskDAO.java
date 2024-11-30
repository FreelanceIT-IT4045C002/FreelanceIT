package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDAO implements ITaskDAO {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task save(Task task) throws Exception {
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new Exception("Error saving task: " + task, e);
        }
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return List.copyOf(tasks);
    }

    public List<Task> findAllByProjectId(int projectId) {
        return taskRepository.findByProject_ProjectId(projectId);
    }

    @Override
    public Task fetch(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found"));
    }

    @Override
    public void delete(int id) {
        taskRepository.deleteById(id);
    }
}


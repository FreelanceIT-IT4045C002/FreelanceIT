package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dto.Task;
import com.freelanceit.freelanceit.dao.ITaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private ITaskDAO taskDAO;

    public TaskService() {
    }

    public TaskService(ITaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public Task fetchById(int id) {
        return taskDAO.fetch(id);
    }

    @Override
    public List<Task> fetchAll() {
        return taskDAO.findAll();
    }

    @Override
    public List<Task> fetchAllByProjectId(int projectId) {
        return taskDAO.findAllByProjectId(projectId);
    }

    @Override
    public void delete(int id) throws Exception {
        taskDAO.delete(id);
    }

    @Override
    public Task save(Task task) throws Exception {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        return taskDAO.save(task);
    }
}


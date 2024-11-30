package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Task;

import java.util.List;

public interface ITaskDAO {
    Task save(Task task) throws Exception;
    List<Task> findAll();
    List<Task> findAllByProjectId(int projectId); // Fetch tasks by project ID
    Task fetch(int id);
    void delete(int id);
}

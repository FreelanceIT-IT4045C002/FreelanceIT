package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dto.Task;

import java.util.List;

public interface ITaskService {
    Task fetchById(int id);
    List<Task> fetchAll();
    List<Task> fetchAllByProjectId(int projectId);
    void delete(int id) throws Exception;
    Task save(Task task) throws Exception;
}


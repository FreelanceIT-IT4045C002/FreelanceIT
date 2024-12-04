package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dto.Project;

import java.util.List;

public interface IProjectService {
    Project fetchById(int id);
    List<Project> fetchAll();
    List<Project> fetchByUserId(int userId);

    void delete(int id) throws Exception;
    Project save(Project project) throws Exception;
}
package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dto.Project;
import com.freelanceit.freelanceit.dao.IProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {
    @Autowired
    private IProjectDAO projectDAO;

    public ProjectService() {
    }

    public ProjectService(IProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @Override
    @Cacheable(value = "project", key = "'project_' + #id")
    public Project fetchById(int id) {
        return projectDAO.fetch(id);
    }

    @Override
    @Cacheable(value = "project", key = "'all_projects'")
    public List<Project> fetchAll() {
        return projectDAO.findAll();
    }

    @Override
    @Cacheable(value = "project", key = "'user_projects_' + #userId")
    public List<Project> fetchByUserId(int userId) {
        return projectDAO.findByUser_Id(userId);
    }

    @Override
    @CacheEvict(value = "project", allEntries = true)
    public void delete(int id) throws Exception {
        projectDAO.delete(id);
    }

    @Override
    @CacheEvict(value = "project", allEntries = true)
    public Project save(Project project) throws Exception {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
        return projectDAO.save(project);
    }
}

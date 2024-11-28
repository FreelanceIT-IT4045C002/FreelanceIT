package com.freelanceit.freelanceit.service;

import com.freelanceit.freelanceit.dto.Project;
import com.freelanceit.freelanceit.dao.IProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Project fetchById(int id) {
        return projectDAO.fetch(id);
    }

    @Override
    public List<Project> fetchAll() {
        return projectDAO.findAll();
    }

    @Override
    public void delete(int id) throws Exception {
        projectDAO.delete(id);
    }

    @Override
    public Project save(Project project) throws Exception {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
        return projectDAO.save(project);
    }

}

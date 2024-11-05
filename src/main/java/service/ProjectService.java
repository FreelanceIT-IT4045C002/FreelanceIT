package service;

import dao.IProjectDAO;
import dto.Project;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectService implements IProjectService {
    @Autowired
    private IProjectDAO projectDAO;

    public ProjectService() { }
    public ProjectService(IProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @Override
    public Project fetchById(int id) {
        return null;
    }

    @Override
    public List<Project> fetchAll() {
        return List.of();
    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public Project save(Project project) throws Exception {
        return null;
    }
}

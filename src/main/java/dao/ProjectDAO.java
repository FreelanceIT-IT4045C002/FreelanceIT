package dao;

import dto.Project;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements IProjectDAO {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project save(Project project) throws Exception {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }

    @Override
    public Project fetch(int id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        projectRepository.deleteById(id);
    }
}

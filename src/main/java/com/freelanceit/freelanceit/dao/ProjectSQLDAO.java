package dao;

import dto.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("projectDAO")
public class ProjectSQLDAO implements IProjectDAO{

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project save(Project project) throws Exception {
        Project createdProject = projectRepository.save(project);
        return createdProject;
    }

    @Override
    public List<Project> findAll() {
        List<Project> allProjects = new ArrayList<Project>();
        Iterable<Project> projects = projectRepository.findAll();
        for (Project project : projects) {
            allProjects.add(project);
        }
        return allProjects;

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

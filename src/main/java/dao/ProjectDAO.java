package dao;

import dto.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectDAO implements IProjectDAO {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project save(Project project) throws Exception {
        try {
            return projectRepository.save(project);
        }
        catch (Exception e) {
            throw new Exception("Error saving project: " + project, e);
        }
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return List.copyOf(projects);
    }


    @Override
    public Project fetch(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + id + " not found"));
    }


    @Override
    public void delete(int id) {
        projectRepository.deleteById(id);
    }
}

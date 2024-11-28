package dao;

import dto.Project;

import java.util.List;

public interface IProjectDAO {
    Project save(Project project) throws Exception;
    List<Project> findAll();
    Project fetch(int id);
    void delete(int id);
}

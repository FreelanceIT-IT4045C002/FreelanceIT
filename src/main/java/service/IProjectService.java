package service;

import dto.Project;

import java.util.List;

public interface IProjectService {
    Project fetchById(int id);
    List<Project> fetchAll();
    //TODO: Fetch all Tasks

    void delete(int id) throws Exception;
    Project save(Project project) throws Exception;
}
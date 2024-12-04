package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * Repository interface for managing Project entities.
 * This interface extends CrudRepository to provide CRUD operations
 * for Project entities with Integer as the ID type.
 */
public interface ProjectRepository extends CrudRepository<Project, Integer> {
    /**
     * Find all projects by user id.
     *
     * @param userId the user id
     * @return the list of projects
     */
    List<Project> findByUser_Id(int userId);
}


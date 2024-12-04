package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for managing Task entities.
 *
 * This interface extends CrudRepository to provide CRUD operations
 * for Task entities with Integer as the ID type. It also includes
 * a method to find tasks associated with a specific project.
 */
public interface TaskRepository extends CrudRepository<Task, Integer> {

    /**
     * Retrieves a list of tasks associated with a specific project
     * identified by its unique project ID.
     *
     * @param projectId the unique identifier of the project
     * @return a list of tasks associated with the specified project
     */
    List<Task> findByProject_ProjectId(int projectId);
}


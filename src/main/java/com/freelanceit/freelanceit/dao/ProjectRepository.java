package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Project;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing Project entities.
 * This interface extends CrudRepository to provide CRUD operations
 * for Project entities with Integer as the ID type.
 */
public interface ProjectRepository extends CrudRepository<Project, Integer> { }

package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    List<Project> findByUser_Id(int userId);
}

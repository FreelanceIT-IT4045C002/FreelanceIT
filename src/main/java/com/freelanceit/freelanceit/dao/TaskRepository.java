package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findByProject_ProjectId(int projectId);

}


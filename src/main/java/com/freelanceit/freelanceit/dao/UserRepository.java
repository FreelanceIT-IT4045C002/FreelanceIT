package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}

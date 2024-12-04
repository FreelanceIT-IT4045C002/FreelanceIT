package com.freelanceit.freelanceit.dao;

import com.freelanceit.freelanceit.dto.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing User entities.
 *
 * This interface extends CrudRepository to provide CRUD operations
 * for User entities with Integer as the ID type. It also includes
 * a method to find a user by their username.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to be retrieved
     * @return the User entity associated with the specified username,
     *         or null if no user is found
     */
    User findByUsername(String username);
}

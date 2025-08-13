package com.gcu.data;

/**
 * Generic interface for user data access operations
 * @param <T> the type of user entity
 */
public interface UsersDataAccessInterface<T> {

    /**
     * Find a user by username
     * @param username the username to search for
     * @return the user entity if found
     */
    T findByUsername(String username);
}
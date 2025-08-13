package com.gcu.data;

import java.util.List;

/**
 * Generic CRUD Interface for data access
 * @param <T> the model type
 */
public interface DataAccessInterface <T>{

    /**
     * Retrieve all records.
     * @return list of T
     */
    List<T> findAll();

    /**
     *  Find one record by its ID
     * @param id the primary key (changed to String for MongoDB)
     * @return the matching T, or null if not found
     */
    T findById(String id);  // Changed from int to String

    /**
     * Insert a new record
     * @param t the object to create
     * @return true if inserted correctly
     */
    boolean create(T t);

    /**
     * Update an existing record
     * @param t the object to update (NEEDS TO INCLUDE ID)
     * @return true if update succeeded
     */
    boolean update(T t);

    /**
     * Delete a record
     * @param t the object to delete (NEEDS to have ID)
     * @return true if delete succeeded
     */
    boolean delete(T t);

}
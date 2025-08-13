package com.gcu.data.repository;

import com.gcu.data.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Order entities
 * Extends MongoRepository to get automatic CRUD operations for MongoDB
 */
@Repository
public interface OrdersRepository extends MongoRepository<OrderEntity, String> {
    // MongoRepository already provides findById(String id) method
    // No need to add getOrderById - we can use the built-in findById
}
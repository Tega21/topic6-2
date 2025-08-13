package com.gcu.topic61.business;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Business Service updated to use MongoDB
 * Now uses DataAccessInterface and delegates to the data layer
 */
public class OrdersBusinessService implements OrdersBusinessServiceInterface {

    @Autowired
    private DataAccessInterface<OrderModel> dataService;

    @Override
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
    }

    /**
     * Get orders from database through the data service
     */
    @Override
    public List<OrderModel> getOrders() {
        return dataService.findAll();
    }

    /**
     * Get a single order by ID
     * @param id the order ID
     * @return OrderModel if found, null otherwise
     */
    @Override
    public OrderModel getOrderById(String id) {
        // Call findById from the dataService
        return dataService.findById(id);
    }

    @Override
    public void init() {
        System.out.println("OrdersBusinessService: init() called");

        // Initialize with some sample data if the database is empty
        List<OrderModel> existingOrders = dataService.findAll();
        if (existingOrders.isEmpty()) {
            System.out.println("Database is empty, creating sample orders...");

            dataService.create(new OrderModel(null, "1001", "PlayStation 5", 499.99f, 1));
            dataService.create(new OrderModel(null, "1002", "27in Monitor", 399.99f, 2));
            dataService.create(new OrderModel(null, "1003", "DualSense Controller", 69.99f, 1));
            dataService.create(new OrderModel(null, "1004", "Microphone", 39.99f, 1));

            System.out.println("Sample orders created successfully!");
        }
    }

    @Override
    public void destroy() {
        System.out.println("OrdersBusinessService: destroy() called");
    }
}
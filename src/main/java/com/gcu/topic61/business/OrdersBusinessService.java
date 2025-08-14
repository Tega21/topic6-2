package com.gcu.topic61.business;

import com.gcu.data.DataAccessInterface;
import com.gcu.data.entity.OrderEntity;
import com.gcu.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service  // Add this annotation
public class OrdersBusinessService implements OrdersBusinessServiceInterface {

    @Autowired
    @Qualifier("orderDataService")
    private DataAccessInterface<OrderEntity> dataService;  // Change to OrderEntity

    @Override
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
    }

    /**
     * Get orders from database through the data service
     * Convert from OrderEntity to OrderModel
     */
    @Override
    public List<OrderModel> getOrders() {
        List<OrderEntity> entities = dataService.findAll();
        List<OrderModel> models = new ArrayList<>();

        for (OrderEntity entity : entities) {
            models.add(new OrderModel(
                    entity.getId(),
                    entity.getOrderNo(),
                    entity.getProductName(),
                    entity.getPrice(),
                    entity.getQuantity()
            ));
        }

        return models;
    }

    /**
     * Get a single order by ID
     * @param id the order ID
     * @return OrderModel if found, null otherwise
     */
    @Override
    public OrderModel getOrderById(String id) {
        OrderEntity entity = dataService.findById(id);
        if (entity != null) {
            return new OrderModel(
                    entity.getId(),
                    entity.getOrderNo(),
                    entity.getProductName(),
                    entity.getPrice(),
                    entity.getQuantity()
            );
        }
        return null;
    }

    @Override
    public void init() {
        System.out.println("OrdersBusinessService: init() called");

        // Initialize with some sample data if the database is empty
        List<OrderEntity> existingOrders = dataService.findAll();
        if (existingOrders.isEmpty()) {
            System.out.println("Database is empty, creating sample orders...");

            // Create OrderEntity objects instead of OrderModel
            dataService.create(new OrderEntity(null, "1001", "PlayStation 5", 499.99f, 1));
            dataService.create(new OrderEntity(null, "1002", "27in Monitor", 399.99f, 2));
            dataService.create(new OrderEntity(null, "1003", "DualSense Controller", 69.99f, 1));
            dataService.create(new OrderEntity(null, "1004", "Microphone", 39.99f, 1));

            System.out.println("Sample orders created successfully!");
        }
    }

    @Override
    public void destroy() {
        System.out.println("OrdersBusinessService: destroy() called");
    }
}
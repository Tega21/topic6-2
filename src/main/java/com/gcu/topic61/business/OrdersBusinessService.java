package com.gcu.topic61.business;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersBusinessService implements OrdersBusinessServiceInterface {

    // Match the DAO's bean name AND generic type
    @Autowired
    @Qualifier("orderDataService")
    private DataAccessInterface<OrderModel> dataService;

    @Override
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
    }

    @Override
    public List<OrderModel> getOrders() {
        // DAO already returns models; no extra conversion needed
        return dataService.findAll();
    }

    @Override
    public OrderModel getOrderById(String id) {
        return dataService.findById(id);
    }

    @Override
    public void init() {
        System.out.println("OrdersBusinessService: init() called");
        List<OrderModel> existing = dataService.findAll();
        if (existing.isEmpty()) {
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

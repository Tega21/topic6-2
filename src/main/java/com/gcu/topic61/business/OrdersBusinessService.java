package com.gcu.topic61.business;

import com.gcu.data.OrderDataService;
import com.gcu.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdersBusinessService implements OrdersBusinessServiceInterface {

    @Autowired
    private OrderDataService dataService;  // Use specific type, no qualifier needed

    @Override
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
    }

    @Override
    public List<OrderModel> getOrders() {
        return dataService.findAll();
    }

    @Override
    public OrderModel getOrderById(String id) {
        return dataService.findById(id);
    }

    @Override
    public void init() {
        System.out.println("OrdersBusinessService: init() called");
    }

    @Override
    public void destroy() {
        System.out.println("OrdersBusinessService: destroy() called");
    }
}
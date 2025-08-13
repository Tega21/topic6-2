package com.gcu.topic61.business;

import com.gcu.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class AnotherOrdersBusinessService implements OrdersBusinessServiceInterface {

    @Override
    public void test() {
        System.out.println("Hello from AnotherOrdersBusinessService");
    }

    @Override
    public List<OrderModel> getOrders() {
        List<OrderModel> orders = new ArrayList<>();

        // Using String IDs instead of Long
        orders.add(new OrderModel("1", "001", "Eggs 60ct", 19.99f, 1));
        orders.add(new OrderModel("2", "002", "Toilet Paper", 21.99f, 2));
        orders.add(new OrderModel("3", "003", "Hot Sauce", 4.99f, 3));
        orders.add(new OrderModel("4", "004", "Tortilla 7ct", 9.99f, 4));
        orders.add(new OrderModel("5", "005", "Dog Food", 30.99f, 5));

        return orders;
    }

    @Override
    public OrderModel getOrderById(String id) {
        // Simply return null as specified
        return null;
    }

    @Override
    public void init() {
        System.out.println("AnotherOrdersBusinessService: init() called");
    }

    @Override
    public void destroy() {
        System.out.println("AnotherOrdersBusinessService: destroy() called");
    }
}
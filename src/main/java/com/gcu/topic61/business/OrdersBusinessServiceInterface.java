package com.gcu.topic61.business;

import com.gcu.model.OrderModel;

import java.util.List;

public interface OrdersBusinessServiceInterface {
    public void test();
    List<OrderModel> getOrders();
    public OrderModel getOrderById(String id);
    void init();
    void destroy();
}
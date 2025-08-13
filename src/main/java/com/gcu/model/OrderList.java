package com.gcu.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "orders")
public class OrderList {
    private List<OrderModel> orders;

    public OrderList() {
    }

    public OrderList(List<OrderModel> orders) {
        this.orders = orders;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}

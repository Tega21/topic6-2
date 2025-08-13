package com.gcu.controller;

import com.gcu.topic61.business.OrdersBusinessServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersBusinessServiceInterface service;

    @GetMapping("/display")
    public String displayOrders(Model model) {
        // Get orders from business service
        model.addAttribute("title", "My Orders");
        model.addAttribute("orders", service.getOrders());

        return "orders";
    }
}
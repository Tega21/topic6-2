package com.gcu.topic61.business;

import java.util.List;

import com.gcu.model.OrderList;
import com.gcu.model.OrderModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class OrdersRestService {

    @Autowired
    private OrdersBusinessServiceInterface service;

    @GetMapping(path = "/getjson", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderModel> getOrdersAsJson() {
        return service.getOrders();
    }

    @GetMapping(path = "/getxml", produces = MediaType.APPLICATION_XML_VALUE)
    public OrderList getOrdersAsXml() {
        return new OrderList(service.getOrders());
    }

    /**
     * Get a single order by ID
     * @param id the order ID from the URL path
     * @return ResponseEntity with the order or appropriate error status
     */
    @GetMapping(path = "/getorder/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderModel> getOrder(@PathVariable("id") String id) {
        try {
            // Call the service to get the order by ID
            OrderModel order = service.getOrderById(id);

            // Check if order was found
            if (order == null) {
                // Return 404 NOT FOUND if order doesn't exist
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Return 200 OK with the order data
            return new ResponseEntity<>(order, HttpStatus.OK);

        } catch (Exception e) {
            // Log the error (in production, use proper logging)
            e.printStackTrace();

            // Return 500 INTERNAL SERVER ERROR for any exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
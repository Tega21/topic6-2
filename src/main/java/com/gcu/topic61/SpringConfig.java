package com.gcu.topic61;

import com.gcu.topic61.business.OrdersBusinessService;
import com.gcu.topic61.business.OrdersBusinessServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean(name = "ordersBusinessService")
    @ConditionalOnMissingBean(OrdersBusinessServiceInterface.class)
    public OrdersBusinessServiceInterface getOrdersBusiness() {
        return new OrdersBusinessService();
    }
}
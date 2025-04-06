package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gcu.business.OrdersBusinessService;
import com.gcu.business.OrdersBusinessServiceInterface;

@Configuration
@ComponentScan("com.gcu")
public class SpringConfig {

    //@Bean(name = "ordersBusinessService", initMethod = "init", destroyMethod = "destroy")
    @Scope("session")
    public OrdersBusinessServiceInterface getOrdersBusiness() {
        return new OrdersBusinessService();
    }
}

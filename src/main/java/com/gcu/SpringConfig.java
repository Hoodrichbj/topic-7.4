package com.gcu;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.gcu")
public class SpringConfig {

    // Removed redundant @Bean definition to prevent conflict with @Service in OrdersBusinessService

    /*
    @Bean(name = "ordersBusinessService", initMethod = "init", destroyMethod = "destroy")
    @Scope("session")
    public OrdersBusinessServiceInterface getOrdersBusiness() {
        return new OrdersBusinessService();
    }
    */
}

package com.gcu.business;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gcu.model.OrderModel;

public class AnotherOrdersBusinessService implements OrdersBusinessServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(AnotherOrdersBusinessService.class);

    @Override
    public List<OrderModel> getOrders() {
        logger.debug("Starting to retrieve orders from AnotherOrdersBusinessService");
        List<OrderModel> orders = new ArrayList<>();
        orders.add(new OrderModel(1L, "00001", "Product 1", 1.0f, 1));
        orders.add(new OrderModel(2L, "00002", "Product 2", 2.0f, 2));
        orders.add(new OrderModel(3L, "00003", "Product 3", 3.0f, 3));
        orders.add(new OrderModel(4L, "00004", "Product 4", 4.0f, 4));
        orders.add(new OrderModel(5L, "00005", "Product 5", 5.0f, 5));
        logger.info("Retrieved {} orders from AnotherOrdersBusinessService", orders.size());
        return orders;
    }

    @Override
    public void init() {
        logger.info("AnotherOrdersBusinessService initialization");
        System.out.print("Method init() has been called.");
    }

    @Override
    public void destroy() {
        logger.info("AnotherOrdersBusinessService destruction");
        System.out.print("Method destroy() has been called.");
    }

    @Override
    public void test() {
        logger.info("AnotherOrdersBusinessService test() method called");
        System.out.println("Test method executed.");
    }
}

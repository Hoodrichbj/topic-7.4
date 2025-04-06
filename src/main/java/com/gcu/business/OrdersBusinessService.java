package com.gcu.business;

import java.util.ArrayList;
import java.util.List;
//jj
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.OrdersDataService;
import com.gcu.data.entity.OrderEntity;
import com.gcu.model.OrderModel;

@Service
public class OrdersBusinessService implements OrdersBusinessServiceInterface {

    @Autowired
    private OrdersDataService service;

    // Logger to track this class's activities
    private static final Logger logger = LoggerFactory.getLogger(OrdersBusinessService.class);

    @Override
    public void test() {
        // Log at INFO level when test method is called
        logger.info("Test method called - OrdersBusinessService");
    }

    @Override
    public List<OrderModel> getOrders() {
        // Log at DEBUG level before fetching orders
        logger.debug("Starting to fetch orders");
        List<OrderEntity> entities = service.findAll();
        List<OrderModel> domainModels = new ArrayList<>();
        for (OrderEntity entity : entities) {
            domainModels.add(new OrderModel(entity.getId(), entity.getOrderNo(), entity.getProductName(), entity.getPrice(), entity.getQuantity()));
            // Log at TRACE level to see detailed process info per order
            logger.trace("Processing order: {}", entity.getOrderNo());
        }
        // Log at INFO level after all orders are fetched
        logger.info("Completed fetching orders");
        return domainModels;
    }

    @Override
    public void init() {
        // Log at INFO level when init method is invoked
        logger.info("Initialization method has been called");
    }

    @Override
    public void destroy() {
        // Log at INFO level when destroy method is invoked
        logger.info("Destroy method has been called");
    }
}

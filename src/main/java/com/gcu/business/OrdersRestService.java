package com.gcu.business;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gcu.model.OrderList;
import com.gcu.model.OrderModel;

@RestController
@RequestMapping("/service")
public class OrdersRestService {

    @Autowired
    private OrdersBusinessServiceInterface service;

    private static final Logger logger = LoggerFactory.getLogger(OrdersRestService.class);

    @GetMapping(path="/getjson", produces="application/json")
    public List<OrderModel> getOrdersAsJson() {
        logger.debug("Request received to get orders as JSON"); // Log request
        return service.getOrders();
    }

    @GetMapping(path="/getxml", produces="application/xml")
    public OrderList getOrdersAsXml() {
        logger.debug("Request received to get orders as XML"); // Log request
        OrderList list = new OrderList();
        list.setOrders(service.getOrders());
        return list;
    }
}

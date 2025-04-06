package com.gcu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    // Logger to track activity within this controller
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private OrdersBusinessServiceInterface service;
    @Autowired
    private SecurityBusinessService security;
    
    // GET method to display the login form
    @GetMapping("/")
    public String display(Model model) {
        // Display Login Form View
        logger.info("Displaying the login form");  // Log that the login form is being displayed
        model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    // POST method to process the login form
    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model) {
        logger.debug("Attempting to log in user: {}", loginModel.getUsername());  // Log attempt to log in with user details

        // Call to the authenticate method
        security.authenticate(loginModel.getUsername(), loginModel.getPassword());
        logger.debug("Authentication attempted for user: {}", loginModel.getUsername());  // Log authentication attempt

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            logger.error("Validation errors present during login attempt for user: {}", loginModel.getUsername());  // Log validation errors
            model.addAttribute("title", "Login Form");
            return "login";
        }

        // Call the getOrders() method
        List<OrderModel> orders = service.getOrders();
        logger.info("Login successful, fetching orders for user: {}", loginModel.getUsername());  // Log successful login and order fetching

        // Display the Orders View
        model.addAttribute("title", "My Orders");
        model.addAttribute("orders", orders);
        return "orders";
    }
}

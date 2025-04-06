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

    /**
     * GET method to display the login form
     * @param model Model to pass data to the view
     * @return login view
     */
    @GetMapping("/")
    public String display(Model model) {
        logger.info("Displaying the login form");
        model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());
        return "login"; // login.html view in /templates/
    }

    /**
     * POST method to process login form
     * @param loginModel LoginModel object populated from form input
     * @param bindingResult Validation result for the model
     * @param model Spring's model for view rendering
     * @return redirect to orders view if successful, otherwise reload login
     */
    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model) {
        logger.debug("Attempting to log in user: {}", loginModel.getUsername());

        // Authenticate the user
        boolean isAuthenticated = security.authenticate(loginModel.getUsername(), loginModel.getPassword());
        logger.debug("Authentication attempted for user: {}", loginModel.getUsername());

        if (!isAuthenticated) {
            logger.warn("Authentication failed for user: {}", loginModel.getUsername());
            model.addAttribute("loginError", "Invalid credentials. Please try again.");
            return "login";
        }

        // Check validation errors
        if (bindingResult.hasErrors()) {
            logger.error("Validation errors during login for user: {}", loginModel.getUsername());
            model.addAttribute("title", "Login Form");
            return "login";
        }

        // Retrieve orders for the authenticated user
        List<OrderModel> orders = service.getOrders();
        logger.info("Login successful, orders fetched for user: {}", loginModel.getUsername());

        model.addAttribute("title", "My Orders");
        model.addAttribute("orders", orders);
        return "orders"; // orders.html view in /templates/
    }
}

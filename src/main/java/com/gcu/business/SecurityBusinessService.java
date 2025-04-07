package com.gcu.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SecurityBusinessService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityBusinessService.class);

    public boolean authenticate(String name, String password) {
        // Log the authentication attempt
        logger.info("Authentication attempt for user: {}", name);
        System.out.println("Hello from the SecurityBusinessService!");
        return true; // Assuming the authentication always returns true for simplicity
    }
}

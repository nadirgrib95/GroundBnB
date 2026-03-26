package com.groundbnb.controller;

import com.groundbnb.dto.CustomerLoginDTO;
import com.groundbnb.dto.CustomerSignupDTO;
import com.groundbnb.entity.Customer;
import com.groundbnb.security.JwtUtils;
import com.groundbnb.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public CustomerController(CustomerService customerService, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody CustomerSignupDTO dto) {
        try {
            log.info("Signup attempt for email: {}", dto.getEmail());
            Customer customer = customerService.register(dto);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            log.error("Signup failed: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerLoginDTO dto) {
        try {
            log.info("Login attempt for email: {}", dto.getEmail());

            // Try to find the customer
            Customer customer = customerService.login(dto.getEmail());

            if (customer == null) {
                log.warn("User not found: {}", dto.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }

            // Check password
            // If using plain text passwords (NoOpPasswordEncoder), use:
            /*if (!customer.getPassword().equals(dto.getPassword())) {
                log.warn("Invalid password for user: {}", dto.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }*/

            // If using BCrypt, use:
             if (!passwordEncoder.matches(dto.getPassword(), customer.getPassword())) {
                 return ResponseEntity.status(401).body("Invalid credentials");
             }

            String token = jwtUtils.generateJwtToken(customer.getEmail());
            log.info("Login successful for: {}", dto.getEmail());

            return ResponseEntity.ok(token);

        } catch (Exception e) {
            log.error("Login error for {}: {}", dto.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
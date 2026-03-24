package com.groundbnb.controller;

import com.groundbnb.dto.CustomerLoginDTO;
import com.groundbnb.dto.CustomerSignupDTO;
import com.groundbnb.entity.Customer;
import com.groundbnb.security.JwtUtils;
import com.groundbnb.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

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
        Customer customer = customerService.register(dto);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerLoginDTO dto) {
        Customer customer = customerService.login(dto.getEmail());

        if (!passwordEncoder.matches(dto.getPassword(), customer.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtils.generateJwtToken(customer.getEmail());

        return ResponseEntity.ok(token);
    }
}
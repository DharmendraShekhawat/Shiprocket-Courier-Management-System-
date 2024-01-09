package com.dharmendra.myRocket.controller;

import com.dharmendra.myRocket.entity.Customer;
import com.dharmendra.myRocket.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<String> addCustomer(Customer customer){
        return customerService.addCustomer(customer);
    }

}

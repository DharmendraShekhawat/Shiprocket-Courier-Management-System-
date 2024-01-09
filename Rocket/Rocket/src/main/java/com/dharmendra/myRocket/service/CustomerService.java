package com.dharmendra.myRocket.service;

import com.dharmendra.myRocket.entity.Customer;
import com.dharmendra.myRocket.repo.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    ICustomerRepo customerRepo;

    public ResponseEntity<String> addCustomer(Customer customer) {
        customerRepo.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("customer added");
    }
}

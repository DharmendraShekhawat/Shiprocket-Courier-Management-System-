package com.dharmendra.myRocket.repo;

import com.dharmendra.myRocket.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepo extends JpaRepository<Customer,Integer> {

}

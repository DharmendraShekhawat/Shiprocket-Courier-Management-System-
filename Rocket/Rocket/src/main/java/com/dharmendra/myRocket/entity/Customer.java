package com.dharmendra.myRocket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String billing_first_name;
    private String billing_last_name;
    private String billing_email;
    private String billing_phone;
    private String billing_address;
    private String billing_address_2;
    private String billing_city;
    private String billing_pinCode;
    private String billing_state;
    private String billing_country;


}

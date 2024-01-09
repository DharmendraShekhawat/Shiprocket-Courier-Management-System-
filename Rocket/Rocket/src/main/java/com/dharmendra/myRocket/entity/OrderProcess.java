package com.dharmendra.myRocket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderProcess {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderProcessId;
    private double sellingPrice;
    private double discount;
    private double tax;
    private double serviceCharge;

    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    Customer customer;
}

package com.dharmendra.myRocket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompanyOrder {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer order_id;

   private LocalDate order_date;

   @Enumerated(value = EnumType.STRING)
   private OrderStatus orderStatus;

   @Enumerated(value = EnumType.STRING)
   private ProductType productType;

   private String comment;

    private String company_name;

    private String pickUp_location;

    private Integer units;

    private Integer weight;
    //payment enum
    private double sellingPrice;
    private double discount;
    private double tax;

    @ManyToOne
    @JoinColumn(name = "fk_company_id")
    Company company;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    Customer customer;
}

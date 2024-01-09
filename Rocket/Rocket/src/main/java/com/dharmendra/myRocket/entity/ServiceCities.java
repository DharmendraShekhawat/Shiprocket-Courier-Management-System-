package com.dharmendra.myRocket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServiceCities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serialNo;

    private Integer pinCode;// max length 6 , min too
    private String city;
    private String state;

}

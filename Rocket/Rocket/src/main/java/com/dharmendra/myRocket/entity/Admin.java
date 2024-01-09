package com.dharmendra.myRocket.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Data
@Component
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;


    private String emailId;

    private String password;

    private String aboutUs;

//    service cities k object ko inject karna h ?
//    private ServiceCities cities;

    private String shipmentCharges;

    private Integer shipmentCollection;
}

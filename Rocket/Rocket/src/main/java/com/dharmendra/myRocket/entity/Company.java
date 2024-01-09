package com.dharmendra.myRocket.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Component
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    private String companyName;

    private Integer companyWallet;

    private String companyContactNumber;

    private String companyEmail;

    private String companyPassword;

    private String companyAddress;

    private String companyCity;

    private String companyState;

}

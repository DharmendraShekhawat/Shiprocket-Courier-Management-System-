package com.dharmendra.myRocket.service;

import com.dharmendra.myRocket.authentication.AdminAuthentication;
import com.dharmendra.myRocket.entity.Admin;
import com.dharmendra.myRocket.entity.ServiceCities;
import com.dharmendra.myRocket.repo.AdminRepo;
import com.dharmendra.myRocket.repo.ServiceCitiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    ServiceCitiesRepo serviceCitiesRepo;

    @Autowired
    AdminAuthentication adminAuthentication;

    public ResponseEntity<String> addAdmin(Admin admin) throws NoSuchAlgorithmException {
        String encryptedPassword = PasswordEncryptor.encrypt(admin.getPassword());
        admin.setPassword(encryptedPassword);
        adminRepo.save(admin);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Admin saved successfully");
    }
    public ResponseEntity<String> addAboutUs(Admin admin) {
        adminRepo.save(admin);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("about us saved");
    }

    public ResponseEntity<String> addServiceCities(String adminEmail_id,String password,ServiceCities serviceCities) throws NoSuchAlgorithmException {
        if (adminAuthentication.authenticateAdmin(adminEmail_id, password)) {
            serviceCitiesRepo.save(serviceCities);

            return ResponseEntity.status(HttpStatus.OK).body("service cities added");
        } else {
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Bad credential");
        }
    }

}

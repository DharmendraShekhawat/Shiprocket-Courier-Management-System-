package com.dharmendra.myRocket.authentication;

import com.dharmendra.myRocket.entity.Admin;
import com.dharmendra.myRocket.repo.AdminRepo;
import com.dharmendra.myRocket.service.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Component
public class AdminAuthentication {

    @Autowired
    AdminRepo adminRepo;
    public boolean authenticateAdmin(String adminEmailId, String password) throws NoSuchAlgorithmException {

         Admin admin = adminRepo.findByEmailId(adminEmailId);
         String encryptedPassword = PasswordEncryptor.encrypt(password);
      return   encryptedPassword.equals(admin.getPassword());
    }
}

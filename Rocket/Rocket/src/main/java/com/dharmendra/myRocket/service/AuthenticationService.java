package com.dharmendra.myRocket.service;

import com.dharmendra.myRocket.entity.Company;
import com.dharmendra.myRocket.repo.ICompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AuthenticationService {

@Autowired
    ICompanyRepo companyRepo;

    public boolean companyAuthenticator(String email, String password) throws NoSuchAlgorithmException {

        Optional<Company> optionalCompany = companyRepo.findFirstByCompanyEmail(email);

        if(optionalCompany.isPresent()){
            String encryptedPassword = PasswordEncryptor.encrypt(password);
          return encryptedPassword.equals(optionalCompany.get().getCompanyPassword());
        }
        else{
            return false;
        }
    }
}

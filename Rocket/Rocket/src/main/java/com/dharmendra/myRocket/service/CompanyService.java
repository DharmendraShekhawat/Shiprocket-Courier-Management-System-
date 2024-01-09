package com.dharmendra.myRocket.service;

import com.dharmendra.myRocket.entity.Admin;
import com.dharmendra.myRocket.entity.Company;
import com.dharmendra.myRocket.entity.companyDTO.CompanySignInDTO;
import com.dharmendra.myRocket.repo.AdminRepo;
import com.dharmendra.myRocket.repo.ICompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    ICompanyRepo companyRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AdminRepo adminRepo;

    //-------------- get about us ----------------
    public ResponseEntity<String> getAboutUs() {
     Admin admin = adminRepo.findAboutUsFromAdminByAdminId(1);
        return ResponseEntity.status(HttpStatus.OK).body(admin.getAboutUs());
    }

    public ResponseEntity<String> addCompany(Company company) throws NoSuchAlgorithmException {

     String encryptedPassword = PasswordEncryptor.encrypt(company.getCompanyPassword());
     company.setCompanyPassword(encryptedPassword);
        companyRepo.save(company);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("company added");
    }

    public ResponseEntity<String> companyWalletRecharge(Integer companyWallet, CompanySignInDTO companySignIn) throws NoSuchAlgorithmException {

        Optional<Company> optionalCompany = companyRepo.findFirstByCompanyEmail(companySignIn.getEmail());

        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            if (authenticationService.companyAuthenticator(companySignIn.getEmail(), companySignIn.getPassword())) {
                Integer existingWallet = company.getCompanyWallet();
                optionalCompany.get().setCompanyWallet(existingWallet + companyWallet);
                companyRepo.save(optionalCompany.get());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("company wallet updated");
            } else {
                return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Credential issue");
            }
        }
    else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please signUp first");
            }
        }



}

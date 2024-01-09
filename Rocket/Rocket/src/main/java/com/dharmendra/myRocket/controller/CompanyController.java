package com.dharmendra.myRocket.controller;

import com.dharmendra.myRocket.entity.Admin;
import com.dharmendra.myRocket.entity.Company;
import com.dharmendra.myRocket.entity.ServiceCities;
import com.dharmendra.myRocket.entity.companyDTO.CompanySignInDTO;
import com.dharmendra.myRocket.service.CompanyService;
import com.dharmendra.myRocket.service.ServiceCitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    ServiceCitiesService serviceCitiesService;

    //------------------ addCompany--------------
    @PostMapping("/company")
    public ResponseEntity<String> addCompany(@RequestBody Company company) throws NoSuchAlgorithmException {
        return companyService.addCompany(company);
    }

    //------------------company wallet recharge-----------
    @PostMapping("/company/wallet/recharge")
    public ResponseEntity<String> companyWalletRecharge(@RequestBody CompanySignInDTO companySignIn,@RequestParam Integer companyWallet) throws NoSuchAlgorithmException {
        return companyService.companyWalletRecharge(companyWallet,companySignIn);
    }

    // ------------------- about us -----------------------------

    @GetMapping("/aboutUs")
    public ResponseEntity<String> getAboutUs(){
        return companyService.getAboutUs();
    }

//    ------------------ service cities ---------------------------
    @GetMapping("/serviceCities")
    public ResponseEntity<List<ServiceCities>> getServiceCities(){
        return serviceCitiesService.getServiceCities();
    }


}

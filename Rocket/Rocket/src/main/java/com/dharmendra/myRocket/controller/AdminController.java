package com.dharmendra.myRocket.controller;

import com.dharmendra.myRocket.entity.Admin;
import com.dharmendra.myRocket.entity.OrderStatus;
import com.dharmendra.myRocket.entity.ServiceCities;
import com.dharmendra.myRocket.exceptionHandling.OrderNotFoundException;
import com.dharmendra.myRocket.service.AdminService;
import com.dharmendra.myRocket.service.CompanyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@RestController
public class AdminController {

    @Autowired
    CompanyOrderService companyOrderService;

    @Autowired
    AdminService adminService;

//  ---------------------- admin ------------------------------
    @PostMapping("/admin")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        return adminService.addAdmin(admin);
    }

 // --------------------- add about us -------------------------
    @PostMapping("/aboutUs")
    public ResponseEntity<String> addAboutUs(@RequestBody Admin admin){
        return adminService.addAboutUs(admin);
    }

 // --------------------- add service cities-------------------------
    @PostMapping("/serviceCities")
    public ResponseEntity<String> addServiceCities(@RequestParam String adminEmail_Id,@RequestParam String password, @RequestBody ServiceCities serviceCities) throws NoSuchAlgorithmException {
        return adminService.addServiceCities(adminEmail_Id, password, serviceCities);
    }

//    ------------------ order status ---------------------------
    @PatchMapping("/order/status")
    public ResponseEntity<String> updateOrderStatus(@RequestParam String adminEmail_Id,@RequestParam String password, @RequestParam Integer orderId, @RequestParam OrderStatus orderStatus) throws OrderNotFoundException, NoSuchAlgorithmException {
        return companyOrderService.updateOrderStatus(adminEmail_Id,password, orderId, orderStatus);
    }


}

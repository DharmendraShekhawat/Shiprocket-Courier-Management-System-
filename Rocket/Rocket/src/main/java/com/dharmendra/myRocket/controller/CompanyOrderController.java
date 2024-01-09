package com.dharmendra.myRocket.controller;

import com.dharmendra.myRocket.entity.CompanyOrder;
import com.dharmendra.myRocket.entity.Customer;
import com.dharmendra.myRocket.entity.companyDTO.CompanyOrderDTO;
import com.dharmendra.myRocket.entity.companyDTO.CompanySignInDTO;
import com.dharmendra.myRocket.exceptionHandling.OrderNotFoundException;
import com.dharmendra.myRocket.service.CompanyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class CompanyOrderController {

    @Autowired
    CompanyOrderService companyOrderService;

    //------------------ company order----------------------------
    @PostMapping("/company/order")
    public ResponseEntity<String> companyOrder(@RequestParam String companyEmailId,@RequestParam String password, @RequestBody CompanyOrderDTO companyOrderDTO) throws NoSuchAlgorithmException {
        return companyOrderService.companyOrder(companyEmailId,password, companyOrderDTO);
    }

    //---------------- update ordered pickupLocation by orderId --------
    @PutMapping("/company/order/pickupLocation/{orderId}")
    public ResponseEntity<String> updateOrderPickupLocation(@RequestBody CompanySignInDTO companySignInDTO, @PathVariable List<Integer> orderId,@RequestParam String pickupLocation) throws OrderNotFoundException, NoSuchAlgorithmException {
        return companyOrderService.updateOrderPickupLocation(companySignInDTO, orderId,pickupLocation);
    }

    //---------------- update ordered deliveryAddress by orderId --------
    @PutMapping("/customer/order/deliveryAddress/{orderId}")
    public ResponseEntity<String> updateOrderDeliveryAddress(@RequestParam String companyEmailId,@RequestParam String password, @PathVariable Integer orderId, @RequestBody Customer customer) throws OrderNotFoundException, NoSuchAlgorithmException {
        return companyOrderService.updateOrderDeliveryAddress(companyEmailId, password, orderId,customer);
    }

    //---------------- update existing order by orderId ----------------
    @PatchMapping("/companyOrder/fields/{orderId}")
    public ResponseEntity<String> updateExistingOrder(@RequestParam String companyEmailId,@RequestParam String password, @PathVariable Integer orderId, @RequestBody CompanyOrder companyOrder) throws OrderNotFoundException, NoSuchAlgorithmException {
        return companyOrderService.updateExistingOrder(companyEmailId,password, orderId,companyOrder);
    }

    //---------------- cancel existing order by orderId ------------------
    @DeleteMapping("/order/cancel/{orderId}") // order to be update cancel
    public ResponseEntity<String> cancelOrderByOrderId(@PathVariable Integer orderId) throws OrderNotFoundException {
        return companyOrderService.cancelOrderByOrderId(orderId);
    }

    // -------------- Get couriers/orders by filter on order date ----------
    @GetMapping("/order/filterOnDate/{orderDate}")
    public ResponseEntity<List<CompanyOrder>> getOrdersFilerOnDate(@RequestBody CompanySignInDTO companySignInDTO,@PathVariable LocalDate orderDate) throws NoSuchAlgorithmException {
          return companyOrderService.getOrdersFilerOnDate(orderDate, companySignInDTO);
    }
}

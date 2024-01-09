package com.dharmendra.myRocket.service;

import com.dharmendra.myRocket.authentication.AdminAuthentication;
import com.dharmendra.myRocket.entity.*;
import com.dharmendra.myRocket.entity.companyDTO.CompanyOrderDTO;
import com.dharmendra.myRocket.entity.companyDTO.CompanySignInDTO;
import com.dharmendra.myRocket.exceptionHandling.OrderNotFoundException;
import com.dharmendra.myRocket.repo.AdminRepo;
import com.dharmendra.myRocket.repo.ICompanyOrderRepo;
import com.dharmendra.myRocket.repo.ICompanyRepo;
import com.dharmendra.myRocket.repo.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyOrderService {

    @Autowired
    ICompanyOrderRepo companyOrderRepo;

   @Autowired
   ICustomerRepo customerRepo;

   @Autowired
   ICompanyRepo companyRepo;

   @Autowired
   AdminRepo adminRepo;

   @Autowired
   ServiceCitiesService pinCodeService;

   @Autowired
   AuthenticationService authenticationService;

   @Autowired
    AdminAuthentication adminAuthentication;


    public ResponseEntity<String> companyOrder(String companyEmailId, String password, CompanyOrderDTO companyOrderDTO) throws NoSuchAlgorithmException {
        if (authenticationService.companyAuthenticator(companyEmailId, password)) {
            CompanyOrder companyOrder = companyOrderDTO.getCompanyOrder();
            // checking service at pickUp & delivery Address

            Integer pickUpPinCode = companyOrderDTO.getPickUpPinCode();
            Integer deliveryPinCode = companyOrderDTO.getDeliveryPinCode();

            if (pinCodeService.checkPinCodeAvailable(pickUpPinCode) && pinCodeService.checkPinCodeAvailable(deliveryPinCode)) {

                Optional<Company> optionalCompany = companyRepo.findById(companyOrder.getCompany().getCompanyId());

                if (optionalCompany.isPresent()) {
                    Integer courierWeight = companyOrder.getWeight();
                    Integer courierUnits = companyOrder.getUnits();
                    Integer courierCharges = courierUnits * courierWeight * 20;

                    if (optionalCompany.get().getCompanyWallet() >= courierCharges) {
                        Company company = optionalCompany.get();

//                        Integer courierChargesPaid = company.getCompanyWallet() - courierCharges;
                        company.setCompanyWallet(company.getCompanyWallet() - courierCharges);
                        companyRepo.save(company);

                        Admin admin = adminRepo.findById(1).get();
                        admin.setShipmentCollection(admin.getShipmentCollection() + courierCharges);
                        adminRepo.save(admin);
                        companyOrderRepo.save(companyOrder);
                        return ResponseEntity.status(HttpStatus.CREATED).body("order successful with orderId  " + companyOrder.getOrder_id());
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("your wallet balance is low first recharge it");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("please signUp first");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("service not available at given pin code");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Bad credentials!");
        }
    }
    public ResponseEntity<String> updateOrderPickupLocation(CompanySignInDTO companySignInDTO, List<Integer> orderId, String pickupLocation) throws OrderNotFoundException, NoSuchAlgorithmException {
        String companyEmailId = companySignInDTO.getEmail();
        String password = companySignInDTO.getPassword();
        if (authenticationService.companyAuthenticator(companyEmailId, password)) {
            List<CompanyOrder> companyOrders = companyOrderRepo.findAllById(orderId);

            if (!companyOrders.isEmpty()) {
                for (CompanyOrder order : companyOrders) {
                    order.setPickUp_location(pickupLocation);
                    companyOrderRepo.save(order);
                }
                return ResponseEntity.status(HttpStatus.OK).body("pickupLocation updated successfully");
            } else {
                throw new OrderNotFoundException("order not found with given orderId ");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Bad credentials!");
        }
    }
    public ResponseEntity<String> updateOrderDeliveryAddress(String companyEmailId,String password, Integer orderId, Customer customer) throws OrderNotFoundException, NoSuchAlgorithmException {

        if (authenticationService.companyAuthenticator(companyEmailId, password)) {
            Optional<CompanyOrder> companyOrder = companyOrderRepo.findById(orderId);
            if (companyOrder.isPresent()) {
                Customer myCustomer = companyOrder.get().getCustomer();
                if (myCustomer != null) {
                    myCustomer.setBilling_address(customer.getBilling_address());
                    myCustomer.setBilling_address_2(customer.getBilling_address_2());
                    myCustomer.setBilling_city(customer.getBilling_city());
                    myCustomer.setBilling_pinCode(customer.getBilling_pinCode());
                    myCustomer.setBilling_state(customer.getBilling_state());
                    myCustomer.setBilling_country(customer.getBilling_country());
                    customerRepo.save(myCustomer);
                    return ResponseEntity.status(HttpStatus.OK).body("Delivery Address updated successfully");
                }
                else{
                    throw new OrderNotFoundException("customer is not link with given orderId ");
                }
            }else {
                throw new OrderNotFoundException("order not found with given orderId ");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Bad credentials!");
        }
    }

    @Transactional
    public ResponseEntity<String> cancelOrderByOrderId(Integer orderId)throws OrderNotFoundException {

        Optional<CompanyOrder> companyOrder = companyOrderRepo.findById(orderId);
        if(companyOrder.isPresent()){
            CompanyOrder companyOrderToBeCancel = companyOrder.get();
            companyOrderToBeCancel.setOrderStatus(OrderStatus.CANCELLED);
            companyOrderRepo.save(companyOrderToBeCancel);
            return ResponseEntity.status(HttpStatus.OK).body("order cancelled successfully");
        }
        else{
            throw new OrderNotFoundException("order not found with given orderId ");
        }
    }


    public ResponseEntity<String> updateExistingOrder(String companyEmailId,String password, Integer orderId, CompanyOrder companyOrder) throws OrderNotFoundException, NoSuchAlgorithmException {

        if(authenticationService.companyAuthenticator(companyEmailId,password)) {
            Optional<CompanyOrder> optionalCompanyOrder = companyOrderRepo.findById(orderId);
            if (optionalCompanyOrder.isPresent()) {

                if (checkOrderStatus(orderId).equals(OrderStatus.ORDERED)) {
                    CompanyOrder companyOrderToBeUpdate = optionalCompanyOrder.get();
                    companyOrderToBeUpdate.setComment(companyOrder.getComment());
                    companyOrderToBeUpdate.setUnits(companyOrder.getUnits());
                    companyOrderToBeUpdate.setWeight(companyOrder.getWeight());
                    companyOrderToBeUpdate.setDiscount(companyOrder.getDiscount());
                    companyOrderToBeUpdate.setSellingPrice(companyOrder.getSellingPrice());
                    companyOrderToBeUpdate.setTax(companyOrder.getTax());
                    companyOrderRepo.save(companyOrderToBeUpdate);
                    return ResponseEntity.status(HttpStatus.OK).body("order updated successfully ");
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body("order Picked-up or In-Transit, So order can't be updated");
                }
            } else {
                throw new OrderNotFoundException("order not found with given orderId ");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Bad credentials!");
        }
    }

    public OrderStatus checkOrderStatus(Integer orderId) throws OrderNotFoundException {
        Optional<CompanyOrder> optionalCompanyOrder = companyOrderRepo.findById(orderId);
        if (optionalCompanyOrder.isPresent() ) {
            return optionalCompanyOrder.get().getOrderStatus();
        }else{
            throw new OrderNotFoundException("order not found with given orderId ");
        }
    }

    public ResponseEntity<String> updateOrderStatus(String adminEmail_Id, String password, Integer orderId, OrderStatus orderStatus) throws OrderNotFoundException, NoSuchAlgorithmException {
        if (adminAuthentication.authenticateAdmin(adminEmail_Id, password)) {
            Optional<CompanyOrder> optionalCompanyOrder = companyOrderRepo.findById(orderId);
            if (optionalCompanyOrder.isPresent()) {
                optionalCompanyOrder.get().setOrderStatus(orderStatus);
                companyOrderRepo.save(optionalCompanyOrder.get());
                return ResponseEntity.status(HttpStatus.OK).body("order status updated");
            } else {
                throw new OrderNotFoundException("order not found with given orderId ");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("bad credentials!");
        }
    }
    public ResponseEntity<List<CompanyOrder>> getOrdersFilerOnDate(LocalDate orderDate, CompanySignInDTO companySignInDTO) throws NoSuchAlgorithmException {
          String companyEmailAddress = companySignInDTO.getEmail();
          String companyPassword = companySignInDTO.getPassword();

          if(authenticationService.companyAuthenticator(companyEmailAddress,companyPassword)){
              Optional<Company> company = companyRepo.findFirstByCompanyEmail(companyEmailAddress);
              Integer companyId = company.get().getCompanyId();
              List<CompanyOrder> companyOrderList = companyOrderRepo.findByCompanyIdOrderDateRange(companyId,orderDate, LocalDate.now());
            return ResponseEntity.status(HttpStatus.OK).body(companyOrderList);
          }
          else{
              return null;
          }


    }





}

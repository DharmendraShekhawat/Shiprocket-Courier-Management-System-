package com.dharmendra.myRocket.entity.companyDTO;

import com.dharmendra.myRocket.entity.CompanyOrder;
import lombok.Data;

@Data
public class CompanyOrderDTO {


    CompanyOrder companyOrder;
    private Integer pickUpPinCode;
    private Integer deliveryPinCode;
}

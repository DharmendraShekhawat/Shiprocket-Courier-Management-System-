package com.dharmendra.myRocket.service;

import com.dharmendra.myRocket.entity.ServiceCities;
import com.dharmendra.myRocket.repo.ServiceCitiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCitiesService {

    @Autowired
    ServiceCitiesRepo serviceCitiesRepo;

    public boolean checkPinCodeAvailable(Integer pinCode) {

      ServiceCities serviceCities =  serviceCitiesRepo.findServiceCitiesByPinCode(pinCode);
      return serviceCities != null;
    }


    public ResponseEntity<List<ServiceCities>> getServiceCities() {
        List<ServiceCities> serviceCities = serviceCitiesRepo.findAll();
      return   ResponseEntity.status(HttpStatus.OK).body(serviceCities);
    }
}

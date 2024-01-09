package com.dharmendra.myRocket.repo;

import com.dharmendra.myRocket.entity.ServiceCities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCitiesRepo extends JpaRepository<ServiceCities, Integer> {

    ServiceCities findServiceCitiesByPinCode(Integer pinCode);
}

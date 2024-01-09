package com.dharmendra.myRocket.repo;

import com.dharmendra.myRocket.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICompanyRepo extends JpaRepository<Company, Integer> {
    Optional<Company> findFirstByCompanyEmail(String email);
}

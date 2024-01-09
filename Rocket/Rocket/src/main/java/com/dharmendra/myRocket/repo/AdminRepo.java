package com.dharmendra.myRocket.repo;

import com.dharmendra.myRocket.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

    @Query(value = "select * from admin where admin_id =:adminId", nativeQuery = true)
   Admin findAboutUsFromAdminByAdminId(Integer adminId);

    @Query(value = "select cities from admin where admin_id =:adminId", nativeQuery = true)
    String findByCitiesFromAdmin(Integer adminId);

    Admin findByEmailId(String adminEmailId);
}

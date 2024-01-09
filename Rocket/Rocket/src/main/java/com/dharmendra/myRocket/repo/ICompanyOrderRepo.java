package com.dharmendra.myRocket.repo;

import com.dharmendra.myRocket.entity.CompanyOrder;
import com.dharmendra.myRocket.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ICompanyOrderRepo extends JpaRepository<CompanyOrder , Integer> {

//    @Query(value = "select * from company_order where fk_company_id =:companyId && order_date >= orderOrder",nativeQuery = true)
//    List<CompanyOrder> findByCompanyIdOrderDate(Integer companyId, LocalDateTime orderDate);

    @Query(value = "SELECT * FROM company_order WHERE fk_company_id = :companyId AND order_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<CompanyOrder> findByCompanyIdOrderDateRange(Integer companyId, LocalDate startDate, LocalDate endDate);

}

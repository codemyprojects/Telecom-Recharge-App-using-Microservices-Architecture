package com.recharge.recharge_service.repository;

import com.recharge.recharge_service.model.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RechargeRepository extends JpaRepository<Recharge, Integer> {
    List<Recharge> findByUserId(String userId);
    List<Recharge> findByMobileNumber(String mobileNumber);
}

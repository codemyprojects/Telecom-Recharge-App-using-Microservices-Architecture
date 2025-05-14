package com.recharge.operator_service.repository;

import com.recharge.operator_service.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OperatorRepository extends JpaRepository<Operator, String> {
    List<Operator> findByType(Operator.OperatorType type);
    List<Operator> findByActiveTrue();
    List<Operator> findByTypeAndActiveTrue(Operator.OperatorType type);
}

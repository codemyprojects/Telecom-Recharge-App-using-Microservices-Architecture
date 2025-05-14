package com.recharge.operator_service.service;

import com.recharge.operator_service.model.Operator;
import com.recharge.operator_service.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OperatorService {
    @Autowired
    private OperatorRepository operatorRepository;

    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    public List<Operator> getActiveOperators() {
        return operatorRepository.findByActiveTrue();
    }

    public List<Operator> getOperatorsByType(Operator.OperatorType type) {
        return operatorRepository.findByType(type);
    }

    public List<Operator> getActiveOperatorsByType(Operator.OperatorType type) {
        return operatorRepository.findByTypeAndActiveTrue(type);
    }

    public Operator getOperatorById(String id) {
        return operatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operator not found with id: " + id));
    }

    public Operator createOperator(Operator operator) {
        if (operator.getId() == null) {
            operator.setId(UUID.randomUUID().toString());
        }
        return operatorRepository.save(operator);
    }

    public Operator updateOperator(String id, Operator operator) {
        Operator existingOperator = getOperatorById(id);

        existingOperator.setName(operator.getName());
        existingOperator.setCode(operator.getCode());
        existingOperator.setType(operator.getType());
        existingOperator.setActive(operator.isActive());

        return operatorRepository.save(existingOperator);
    }

    public void deleteOperator(String id) {
        operatorRepository.deleteById(id);
    }
}

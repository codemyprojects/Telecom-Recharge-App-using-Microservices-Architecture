package com.recharge.operator_service.controller;


import com.recharge.operator_service.model.Operator;
import com.recharge.operator_service.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
public class OperatorController {
    @Autowired
    private OperatorService operatorService;

    @GetMapping
    public ResponseEntity<List<Operator>> getAllOperators() {
        return ResponseEntity.ok(operatorService.getAllOperators());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Operator>> getActiveOperators() {
        return ResponseEntity.ok(operatorService.getActiveOperators());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Operator>> getOperatorsByType(@PathVariable Operator.OperatorType type) {
        return ResponseEntity.ok(operatorService.getOperatorsByType(type));
    }

    @GetMapping("/active/type/{type}")
    public ResponseEntity<List<Operator>> getActiveOperatorsByType(@PathVariable Operator.OperatorType type) {
        return ResponseEntity.ok(operatorService.getActiveOperatorsByType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operator> getOperatorById(@PathVariable String id) {
        return ResponseEntity.ok(operatorService.getOperatorById(id));
    }

    @PostMapping
    public ResponseEntity<Operator> createOperator(@RequestBody Operator operator) {
        return ResponseEntity.ok(operatorService.createOperator(operator));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operator> updateOperator(@PathVariable String id, @RequestBody Operator operator) {
        return ResponseEntity.ok(operatorService.updateOperator(id, operator));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperator(@PathVariable String id) {
        operatorService.deleteOperator(id);
        return ResponseEntity.noContent().build();
    }
}

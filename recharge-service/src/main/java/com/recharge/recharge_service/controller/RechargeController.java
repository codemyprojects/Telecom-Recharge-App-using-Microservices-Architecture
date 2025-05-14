package com.recharge.recharge_service.controller;

import com.recharge.recharge_service.dto.RechargeRequest;
import com.recharge.recharge_service.dto.RechargeResponse;
import com.recharge.recharge_service.model.Recharge;
import com.recharge.recharge_service.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recharge")
public class RechargeController {
    @Autowired
    private RechargeService rechargeService;

    @PostMapping("/process")
    public ResponseEntity<RechargeResponse> processRecharge(@RequestBody RechargeRequest request) {
        return ResponseEntity.ok(rechargeService.processRecharge(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recharge>> getRechargesByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(rechargeService.getRechargesByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recharge> getRechargeById(@PathVariable Long id) {
        return ResponseEntity.ok(rechargeService.getRechargeById(id));
    }
}

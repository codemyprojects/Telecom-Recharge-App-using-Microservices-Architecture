package com.recharge.recharge_service.dto;

import com.recharge.recharge_service.model.Recharge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargeRequest {

    private String userId;
    private String mobileNumber;
    private BigDecimal amount;
    private Recharge.RechargeType type;
    private String operatorId;
    private String paymentMethod;
}

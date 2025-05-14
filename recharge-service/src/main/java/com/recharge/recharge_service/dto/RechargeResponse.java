package com.recharge.recharge_service.dto;

import com.recharge.recharge_service.model.Recharge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargeResponse {
    private Long id;
    private String userId;
    private String mobileNumber;
    private BigDecimal amount;
    private Recharge.RechargeType type;
    private Recharge.RechargeStatus status;
    private String operatorId;
    private String operatorName;
    private String transactionId;
    private LocalDateTime createdAt;
}

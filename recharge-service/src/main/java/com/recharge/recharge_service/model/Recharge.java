package com.recharge.recharge_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String mobileNumber;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private RechargeType type;

    @Enumerated(EnumType.STRING)
    private RechargeStatus status;

    private String operatorId;
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum RechargeType {
        MOBILE, DTH, DATACARD, POSTPAID, ELECTRICITY, GAS, WATER
    }

    public enum RechargeStatus {
        PENDING, SUCCESS, FAILED
    }
}

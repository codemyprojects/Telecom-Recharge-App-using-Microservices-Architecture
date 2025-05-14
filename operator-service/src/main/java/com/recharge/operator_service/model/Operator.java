package com.recharge.operator_service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operator {
    @Id
    private String id;

    private String name;
    private String code;

    @Enumerated(EnumType.STRING)
    private OperatorType type;

    private boolean active;

    public enum OperatorType {
        MOBILE, DTH, DATACARD, POSTPAID, ELECTRICITY, GAS, WATER
    }
}

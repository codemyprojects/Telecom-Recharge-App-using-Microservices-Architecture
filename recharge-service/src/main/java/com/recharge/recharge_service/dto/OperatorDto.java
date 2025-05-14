package com.recharge.recharge_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatorDto {
    private String id;
    private String name;
    private String type;
    private String code;
}

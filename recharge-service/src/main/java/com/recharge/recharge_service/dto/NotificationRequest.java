package com.recharge.recharge_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private String userId;
    private String mobileNumber;
    private String message;
    private String type;
}

package com.recharge.recharge_service.client;


import com.recharge.recharge_service.dto.PaymentRequest;
import com.recharge.recharge_service.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentClient {
    @PostMapping("/api/payments/process")
    PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest);
}

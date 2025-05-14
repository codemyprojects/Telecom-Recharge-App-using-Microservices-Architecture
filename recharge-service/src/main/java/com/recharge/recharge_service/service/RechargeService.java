package com.recharge.recharge_service.service;

import com.recharge.recharge_service.client.NotificationClient;
import com.recharge.recharge_service.client.OperatorClient;
import com.recharge.recharge_service.client.PaymentClient;
import com.recharge.recharge_service.dto.*;
import com.recharge.recharge_service.model.Recharge;
import com.recharge.recharge_service.repository.RechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RechargeService {
    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private OperatorClient operatorClient;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private NotificationClient notificationClient;

    public RechargeResponse processRecharge(RechargeRequest request) {
        // Create a new recharge record
        Recharge recharge = new Recharge();
        recharge.setUserId(request.getUserId());
        recharge.setMobileNumber(request.getMobileNumber());
        recharge.setAmount(request.getAmount());
        recharge.setType(request.getType());
        recharge.setOperatorId(request.getOperatorId());
        recharge.setStatus(Recharge.RechargeStatus.PENDING);
        recharge.setCreatedAt(LocalDateTime.now());
        recharge.setUpdatedAt(LocalDateTime.now());

        // Save the recharge record
        recharge = rechargeRepository.save(recharge);

        // Process payment
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUserId(request.getUserId());
        paymentRequest.setAmount(request.getAmount());
        paymentRequest.setPaymentMethod(request.getPaymentMethod());
        paymentRequest.setReferenceId(recharge.getId().toString());

        PaymentResponse paymentResponse = paymentClient.processPayment(paymentRequest);

        // Update recharge status based on payment response
        if ("SUCCESS".equals(paymentResponse.getStatus())) {
            recharge.setStatus(Recharge.RechargeStatus.SUCCESS);
            recharge.setTransactionId(paymentResponse.getTransactionId());
        } else {
            recharge.setStatus(Recharge.RechargeStatus.FAILED);
        }

        recharge.setUpdatedAt(LocalDateTime.now());
        recharge = rechargeRepository.save(recharge);

        // Send notification
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setUserId(request.getUserId());
        notificationRequest.setMobileNumber(request.getMobileNumber());
        notificationRequest.setType("RECHARGE");

        if (recharge.getStatus() == Recharge.RechargeStatus.SUCCESS) {
            notificationRequest.setMessage("Your recharge of " + request.getAmount() +
                    " for " + request.getMobileNumber() + " was successful.");
        } else {
            notificationRequest.setMessage("Your recharge of " + request.getAmount() +
                    " for " + request.getMobileNumber() + " has failed.");
        }

        notificationClient.sendNotification(notificationRequest);

        // Get operator details
        OperatorDto operator = operatorClient.getOperatorById(request.getOperatorId());

        // Create response
        RechargeResponse response = new RechargeResponse();
        response.setId(recharge.getId());
        response.setUserId(recharge.getUserId());
        response.setMobileNumber(recharge.getMobileNumber());
        response.setAmount(recharge.getAmount());
        response.setType(recharge.getType());
        response.setStatus(recharge.getStatus());
        response.setOperatorId(recharge.getOperatorId());
        response.setOperatorName(operator.getName());
        response.setTransactionId(recharge.getTransactionId());
        response.setCreatedAt(recharge.getCreatedAt());

        return response;
    }

    public List<Recharge> getRechargesByUserId(String userId) {
        return rechargeRepository.findByUserId(userId);
    }

    public Recharge getRechargeById(Long id) {
        return rechargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recharge not found with id: " + id));
    }
}

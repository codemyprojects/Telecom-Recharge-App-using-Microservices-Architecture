package com.recharge.payment_service.service;

import com.recharge.payment_service.dto.PaymentRequest;
import com.recharge.payment_service.dto.PaymentResponse;
import com.recharge.payment_service.model.Payment;
import com.recharge.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentResponse processPayment(PaymentRequest request) {
        // Create a new payment record
        Payment payment = new Payment();
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(Payment.PaymentMethod.valueOf(request.getPaymentMethod()));
        payment.setStatus(Payment.PaymentStatus.PENDING);
        payment.setReferenceId(request.getReferenceId());
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        // Save the payment record
        payment = paymentRepository.save(payment);

        // Process payment with payment gateway (simulated)
        boolean paymentSuccess = processWithPaymentGateway(payment);

        // Update payment status
        if (paymentSuccess) {
            payment.setStatus(Payment.PaymentStatus.SUCCESS);
            payment.setGatewayResponse("Payment processed successfully");
        } else {
            payment.setStatus(Payment.PaymentStatus.FAILED);
            payment.setGatewayResponse("Payment processing failed");
        }

        payment.setUpdatedAt(LocalDateTime.now());
        payment = paymentRepository.save(payment);

        // Create response
        PaymentResponse response = new PaymentResponse();
        response.setTransactionId(payment.getTransactionId());
        response.setStatus(payment.getStatus().toString());
        response.setMessage(payment.getGatewayResponse());

        return response;
    }

    private boolean processWithPaymentGateway(Payment payment) {
        // Simulate payment gateway processing
        // In a real application, this would integrate with a payment gateway API
        try {
            // Simulate processing time
            Thread.sleep(1000);

            // Simulate success (90% success rate)
            return Math.random() < 0.9;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public List<Payment> getPaymentsByUserId(String userId) {
        return paymentRepository.findByUserId(userId);
    }

    public List<Payment> getPaymentsByReferenceId(String referenceId) {
        return paymentRepository.findByReferenceId(referenceId);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }
}

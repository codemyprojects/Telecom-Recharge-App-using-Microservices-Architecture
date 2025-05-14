package com.recharge.notification_service.service;


import com.recharge.notification_service.dto.NotificationRequest;
import com.recharge.notification_service.model.Notification;
import com.recharge.notification_service.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest request) {
        // Create notification record
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setRecipient(request.getMobileNumber());
        notification.setMessage(request.getMessage());
        notification.setType(Notification.NotificationType.valueOf(request.getType()));
        notification.setStatus(Notification.NotificationStatus.PENDING);
        notification.setCreatedAt(LocalDateTime.now());

        // Save notification
        notification = notificationRepository.save(notification);

        // Send notification based on type
        boolean sent = false;
        switch (notification.getType()) {
            case SMS:
                sent = sendSms(notification);
                break;
            case EMAIL:
                sent = sendEmail(notification);
                break;
            case PUSH:
                sent = sendPushNotification(notification);
                break;
        }

        // Update notification status
        if (sent) {
            notification.setStatus(Notification.NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());
        } else {
            notification.setStatus(Notification.NotificationStatus.FAILED);
        }

        notificationRepository.save(notification);
    }

    private boolean sendSms(Notification notification) {
        // Simulate sending SMS
        // In a real application, this would integrate with an SMS gateway
        try {
            // Simulate processing time
            Thread.sleep(500);

            // Simulate success (95% success rate)
            return Math.random() < 0.95;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private boolean sendEmail(Notification notification) {
        // Simulate sending email
        // In a real application, this would integrate with an email service
        try {
            // Simulate processing time
            Thread.sleep(500);

            // Simulate success (98% success rate)
            return Math.random() < 0.98;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private boolean sendPushNotification(Notification notification) {
        // Simulate sending push notification
        // In a real application, this would integrate with a push notification service
        try {
            // Simulate processing time
            Thread.sleep(500);

            // Simulate success (90% success rate)
            return Math.random() < 0.9;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public List<Notification> getNotificationsByUserId(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getNotificationsByType(Notification.NotificationType type) {
        return notificationRepository.findByType(type);
    }

    public List<Notification> getNotificationsByStatus(Notification.NotificationStatus status) {
        return notificationRepository.findByStatus(status);
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }
}

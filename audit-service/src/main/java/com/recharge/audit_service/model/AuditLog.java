package com.recharge.audit_service.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String serviceName;
    private String actionName;

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    private String resourceId;

    @Lob
    private String requestData;

    @Lob
    private String responseData;

    private String ipAddress;
    private String userAgent;
    private LocalDateTime timestamp;

    public enum ActionType {
        CREATE, READ, UPDATE, DELETE, LOGIN, LOGOUT, PAYMENT, RECHARGE
    }
}

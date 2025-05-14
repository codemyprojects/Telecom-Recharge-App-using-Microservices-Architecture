package com.recharge.audit_service.dto;

import com.recharge.audit_service.model.AuditLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogRequest {
    private String userId;
    private String serviceName;
    private String actionName;
    private AuditLog.ActionType actionType;
    private String resourceId;
    private String requestData;
    private String responseData;
    private String ipAddress;
    private String userAgent;
}

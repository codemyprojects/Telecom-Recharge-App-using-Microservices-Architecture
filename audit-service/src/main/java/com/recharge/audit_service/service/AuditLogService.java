package com.recharge.audit_service.service;


import com.recharge.audit_service.Repository.AuditLogRepository;
import com.recharge.audit_service.dto.AuditLogRequest;
import com.recharge.audit_service.model.AuditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public AuditLog createAuditLog(AuditLogRequest request) {
        AuditLog auditLog = new AuditLog();
        auditLog.setUserId(request.getUserId());
        auditLog.setServiceName(request.getServiceName());
        auditLog.setActionName(request.getActionName());
        auditLog.setActionType(request.getActionType());
        auditLog.setResourceId(request.getResourceId());
        auditLog.setRequestData(request.getRequestData());
        auditLog.setResponseData(request.getResponseData());
        auditLog.setIpAddress(request.getIpAddress());
        auditLog.setUserAgent(request.getUserAgent());
        auditLog.setTimestamp(LocalDateTime.now());

        return auditLogRepository.save(auditLog);
    }

    public List<AuditLog> getAuditLogsByUserId(String userId) {
        return auditLogRepository.findByUserId(userId);
    }

    public List<AuditLog> getAuditLogsByServiceName(String serviceName) {
        return auditLogRepository.findByServiceName(serviceName);
    }

    public List<AuditLog> getAuditLogsByActionType(AuditLog.ActionType actionType) {
        return auditLogRepository.findByActionType(actionType);
    }

    public List<AuditLog> getAuditLogsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return auditLogRepository.findByTimestampBetween(start, end);
    }

    public AuditLog getAuditLogById(Long id) {
        return auditLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audit log not found with id: " + id));
    }

}

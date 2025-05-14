package com.recharge.audit_service.Repository;

import com.recharge.audit_service.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    public List<AuditLog> findByUserId(String userId);
    public List<AuditLog> findByServiceName(String serviceName);
    public List<AuditLog> findByActionType(AuditLog.ActionType actionType);
    public List<AuditLog> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}

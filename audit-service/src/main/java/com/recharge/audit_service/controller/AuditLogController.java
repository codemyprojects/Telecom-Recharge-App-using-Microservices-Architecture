package com.recharge.audit_service.controller;

import com.recharge.audit_service.dto.AuditLogRequest;
import com.recharge.audit_service.model.AuditLog;
import com.recharge.audit_service.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

public class AuditLogController {
    @Autowired
    private AuditLogService auditLogService;

    @PostMapping
    public ResponseEntity<AuditLog> createAuditLog(@RequestBody AuditLogRequest request) {
        return ResponseEntity.ok(auditLogService.createAuditLog(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AuditLog>> getAuditLogsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(auditLogService.getAuditLogsByUserId(userId));
    }

    @GetMapping("/service/{serviceName}")
    public ResponseEntity<List<AuditLog>> getAuditLogsByServiceName(@PathVariable String serviceName) {
        return ResponseEntity.ok(auditLogService.getAuditLogsByServiceName(serviceName));
    }

    @GetMapping("/action/{actionType}")
    public ResponseEntity<List<AuditLog>> getAuditLogsByActionType(@PathVariable AuditLog.ActionType actionType) {
        return ResponseEntity.ok(auditLogService.getAuditLogsByActionType(actionType));
    }

    @GetMapping("/time-range")
    public ResponseEntity<List<AuditLog>> getAuditLogsByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(auditLogService.getAuditLogsByTimeRange(start, end));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getAuditLogById(@PathVariable Long id) {
        return ResponseEntity.ok(auditLogService.getAuditLogById(id));
    }
}

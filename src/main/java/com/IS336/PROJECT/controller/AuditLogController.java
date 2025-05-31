package com.IS336.PROJECT.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;
import com.IS336.PROJECT.repository.AuditLogRepository;
import com.IS336.PROJECT.model.AuditLog;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuditLogController {
    private final AuditLogRepository auditLogRepository;
    
    @GetMapping("/audit-logs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AuditLog>> showAuditLogs() {
        return ResponseEntity.ok(auditLogRepository.findAll());
    }
}

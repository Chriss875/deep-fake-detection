package com.IS336.PROJECT.repository;

import com.IS336.PROJECT.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}

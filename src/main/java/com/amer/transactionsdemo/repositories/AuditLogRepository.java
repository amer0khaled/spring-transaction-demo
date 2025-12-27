package com.amer.transactionsdemo.repositories;

import com.amer.transactionsdemo.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}

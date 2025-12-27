package com.amer.transactionsdemo.services;

import com.amer.transactionsdemo.entities.AuditLog;
import com.amer.transactionsdemo.repositories.AuditLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {

    private final AuditLogRepository auditRepo;

    public AuditService(AuditLogRepository auditRepo) {
        this.auditRepo = auditRepo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String message) {
        auditRepo.save(new AuditLog(message));
    }
}


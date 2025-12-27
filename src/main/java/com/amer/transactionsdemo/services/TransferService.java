package com.amer.transactionsdemo.services;

import com.amer.transactionsdemo.entities.Account;
import com.amer.transactionsdemo.exceptions.AccountNotFoundException;
import com.amer.transactionsdemo.exceptions.InsufficientBalanceException;
import com.amer.transactionsdemo.exceptions.TransferAmountTooLargeException;
import com.amer.transactionsdemo.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final AccountRepository accountRepo;
    private final AuditService auditService;

    public TransferService(AccountRepository accountRepo,
                           AuditService auditService) {
        this.accountRepo = accountRepo;
        this.auditService = auditService;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED
    )
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        Account from = accountRepo.findById(fromId)
                .orElseThrow(() -> new AccountNotFoundException(fromId));

        Account to = accountRepo.findById(toId)
                .orElseThrow(() -> new AccountNotFoundException(toId));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }

        auditService.log("Transfer started from " + fromId + " to " + toId);

        from.setBalance(from.getBalance().subtract(amount));
        accountRepo.save(from);

        if (amount.compareTo(BigDecimal.valueOf(1000)) > 0) {
            throw new TransferAmountTooLargeException(amount);
        }

        to.setBalance(to.getBalance().add(amount));
        accountRepo.save(to);

        auditService.log("Transfer completed");
    }


}

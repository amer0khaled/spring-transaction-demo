package com.amer.transactionsdemo.exceptions;

import java.math.BigDecimal;

public class TransferAmountTooLargeException extends RuntimeException {
    public TransferAmountTooLargeException(BigDecimal amount) {
        super("Transfer amount too large: " + amount);
    }
}
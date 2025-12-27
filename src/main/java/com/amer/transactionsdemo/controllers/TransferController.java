package com.amer.transactionsdemo.controllers;

import com.amer.transactionsdemo.services.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping
    public String transfer(@RequestParam Long from,
                           @RequestParam Long to,
                           @RequestParam BigDecimal amount) {

        System.out.println("Incoming transfer request");
        service.transfer(from, to, amount);
        return "Transfer successful";
    }
}

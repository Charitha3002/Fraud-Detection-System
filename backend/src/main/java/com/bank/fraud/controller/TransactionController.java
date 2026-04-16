package com.bank.fraud.controller;

import com.bank.fraud.model.Transaction;
import com.bank.fraud.service.FraudDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private FraudDetectionService fraudService;

    @PostMapping
    public ResponseEntity<Map<String, String>> processTransaction(@RequestBody Transaction transaction) {
        Map<String, String> response = new HashMap<>();
        
        boolean isFraud = fraudService.isFraudulent(transaction);
        
        if (isFraud) {
            response.put("status", "REJECTED");
            response.put("message", "Transaction flagged as potentially fraudulent.");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        response.put("status", "APPROVED");
        response.put("message", "Transaction successful.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

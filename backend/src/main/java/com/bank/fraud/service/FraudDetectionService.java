package com.bank.fraud.service;

import com.bank.fraud.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FraudDetectionService {

    @Autowired
    private RuleFactory ruleFactory;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ml.service.url}")
    private String mlServiceUrl;

    public boolean isFraudulent(Transaction transaction) {
        // Step 1: Evaluate Rule-based algorithms
        List<RuleFactory.FraudRule> rules = ruleFactory.getActiveRules();
        for (RuleFactory.FraudRule rule : rules) {
            if (rule.evaluate(transaction)) {
                System.out.println("Fraud detected by Rule! Txn ID: " + transaction.getTransactionId());
                return true;
            }
        }

        // Step 2: ML-driven check
        try {
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("amount", transaction.getAmount());
            requestParams.put("location", transaction.getLocation());

            ResponseEntity<Map> response = restTemplate.postForEntity(mlServiceUrl, requestParams, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Object isFraudFlag = response.getBody().get("fraud");
                if (isFraudFlag != null && (Boolean) isFraudFlag) {
                    System.out.println("Fraud detected by ML Service! Txn ID: " + transaction.getTransactionId());
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Error calling ML service: " + e.getMessage());
            // Fallback strategy if ML fails
        }

        return false;
    }
}

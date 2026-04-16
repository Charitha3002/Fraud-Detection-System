package com.bank.fraud.service;

import com.bank.fraud.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RuleFactory {

    public interface FraudRule {
        boolean evaluate(Transaction transaction);
    }

    public static class AmountRule implements FraudRule {
        @Override
        public boolean evaluate(Transaction tx) {
            SystemConfig config = SystemConfig.getInstance();
            return tx.getAmount() > config.getMaxTransactionAmount();
        }
    }

    public static class SuspiciousLocationRule implements FraudRule {
        @Override
        public boolean evaluate(Transaction tx) {
            // Dummy logic: flag transactions from certain locations
            return "Nowhere".equalsIgnoreCase(tx.getLocation());
        }
    }

    public List<FraudRule> getActiveRules() {
        List<FraudRule> rules = new ArrayList<>();
        rules.add(new AmountRule());
        rules.add(new SuspiciousLocationRule());
        return rules;
    }
}

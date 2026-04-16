package com.bank.fraud.model;

import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private String accountId;
    private double amount;
    private LocalDateTime timestamp;
    private String location;

    // Constructors, Getters and Setters
    public Transaction() {}

    public Transaction(String transactionId, String accountId, double amount, LocalDateTime timestamp, String location) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.location = location;
    }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}

package com.bank.fraud.service;

public class SystemConfig {

    private static SystemConfig instance;

    private double maxTransactionAmount = 10000.0;
    private int maxTransactionsPerDay = 5;

    private SystemConfig() {
        // Private constructor to prevent instantiation
    }

    public static synchronized SystemConfig getInstance() {
        if (instance == null) {
            instance = new SystemConfig();
        }
        return instance;
    }

    public double getMaxTransactionAmount() {
        return maxTransactionAmount;
    }

    public void setMaxTransactionAmount(double maxTransactionAmount) {
        this.maxTransactionAmount = maxTransactionAmount;
    }

    public int getMaxTransactionsPerDay() {
        return maxTransactionsPerDay;
    }

    public void setMaxTransactionsPerDay(int maxTransactionsPerDay) {
        this.maxTransactionsPerDay = maxTransactionsPerDay;
    }
}

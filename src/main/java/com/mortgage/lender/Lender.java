package com.mortgage.lender;

public class Lender {
    private double currentBalance=100000.00;

    public double getFunds() {
        return currentBalance;
    }

    public void addFunds(double amount) {
        if (amount <= 0) {
            throw new IllegalStateException("Amount too small than the accepted amount");
        }
        currentBalance =currentBalance +amount;
    }
}

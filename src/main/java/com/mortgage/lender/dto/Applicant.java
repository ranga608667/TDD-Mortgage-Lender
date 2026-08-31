package com.mortgage.lender.dto;

import java.time.LocalDate;

public class Applicant {
    private String id;
    private double requestedAmount;
    private int debtToIncomeRatio;
    private int creditScore;
    private double savings;
    private LocalDate date;


    public Applicant(String id, double requestedAmount, int debtToIncomeRatio, int creditScore, double savings, LocalDate date) {
        this.setId(id);
        this.setRequestedAmount(requestedAmount);
        this.setDebtToIncomeRatio(debtToIncomeRatio);
        this.setCreditScore(creditScore);
        this.setSavings(savings);
        this.setDate(date);
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        if (requestedAmount <= 0) {
            throw new IllegalArgumentException("Requested amount must be positive");
        }
        this.requestedAmount = requestedAmount;
    }

    public int getDebtToIncomeRatio() {
        return debtToIncomeRatio;
    }

    public void setDebtToIncomeRatio(int debtToIncomeRatio) {
        if (debtToIncomeRatio < 0) {
            throw new IllegalArgumentException("Debt to income ratio cannot be negative");
        }
        this.debtToIncomeRatio = debtToIncomeRatio;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        if (creditScore < 0 || creditScore > 850) {
            throw new IllegalArgumentException("Credit score must be between 0 and 850");
        }
        this.creditScore = creditScore;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        if (savings < 0) {
            throw new IllegalArgumentException("Savings cannot be negative");
        }
        this.savings = savings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
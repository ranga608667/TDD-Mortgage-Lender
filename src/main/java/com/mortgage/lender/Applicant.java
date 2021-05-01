package com.mortgage.lender;

import java.time.LocalDate;

public class Applicant {
    private String id;
    private double requestedAmount;
    private int dtl;
    private int creditScore;
    private double savings;
    private LocalDate date;


    public Applicant(String id,double requestedAmount, int dtl, int creditScore, double savings, LocalDate date) {
        this.setId(id);
        this.setRequestedAmount(requestedAmount);
        this.setDtl(dtl);
        this.setCreditScore(creditScore);
        this.setSavings(savings);
        this.setDate(date);
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public int getDtl() {
        return dtl;
    }

    public void setDtl(int dtl) {
        this.dtl = dtl;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

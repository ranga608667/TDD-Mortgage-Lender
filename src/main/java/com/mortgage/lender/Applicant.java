package com.mortgage.lender;

public class Applicant {
    private double requestedAmount;
    private int dtl;
    private int creditScore;
    private double savings;


    public Applicant(double requestedAmount, int dtl, int creditScore, double savings) {
        this.setRequestedAmount(requestedAmount);
        this.setDtl(dtl);
        this.setCreditScore(creditScore);
        this.setSavings(savings);
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
}

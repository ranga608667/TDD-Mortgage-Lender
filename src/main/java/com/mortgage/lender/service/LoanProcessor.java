package com.mortgage.lender.service;

import com.mortgage.lender.dto.Applicant;
import com.mortgage.lender.dto.LoanApplicationResult;
import com.mortgage.lender.dto.LoanApplicationStatus;

public class LoanProcessor {
    public static final String QUALIFIED = "qualified";
    public static final String PARTIALLY_QUALIFIED = "partially qualified";
    public static final String DENIED = "denied";
    
    private static final int MIN_DEBT_TO_INCOME_RATIO = 36;
    private static final int MIN_CREDIT_SCORE = 620;
    private static final double MIN_SAVINGS_PERCENTAGE = 25.0;
    
    public static LoanApplicationResult process(Applicant applicant) {
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant cannot be null");
        }
        
        // Validate that requested amount is positive
        if (applicant.getRequestedAmount() <= 0) {
            throw new IllegalArgumentException("Requested amount must be positive");
        }
        
        // Validate savings are not negative
        if (applicant.getSavings() < 0) {
            throw new IllegalArgumentException("Savings cannot be negative");
        }
        
        double savingsPercentage = (applicant.getSavings() / applicant.getRequestedAmount()) * 100;
        
        if (applicant.getDebtToIncomeRatio() < MIN_DEBT_TO_INCOME_RATIO && applicant.getCreditScore() >= MIN_CREDIT_SCORE) {
            if (savingsPercentage >= MIN_SAVINGS_PERCENTAGE) {
                return new LoanApplicationResult(QUALIFIED, applicant.getRequestedAmount(), LoanApplicationStatus.QUALIFIED, applicant);
            } else {
                double loanAmount = 4 * applicant.getSavings();
                return new LoanApplicationResult(PARTIALLY_QUALIFIED, loanAmount, LoanApplicationStatus.PARTIALLY_QUALIFIED, applicant);
            }
        } else {
            return new LoanApplicationResult(DENIED, 0, LoanApplicationStatus.DENIED, applicant);
        }
    }
}
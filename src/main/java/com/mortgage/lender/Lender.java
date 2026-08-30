package com.mortgage.lender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Lender {
    private double currentBalance;
    private double pendingFunds;
    private HashMap<String, LoanApplicationResult> loanStatus = new HashMap<>();
    
    // No-argument constructor for Spring dependency injection
    public Lender() {
        this.currentBalance = 400000.0; // Default initial balance
        this.pendingFunds = 400000.0; // Default pending funds
    }
    
    // Constructor with initial balance - used for testing or explicit initialization
    public Lender(double currentBalance){
        if (currentBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.currentBalance = currentBalance;
    }
    
    @Value("${lender.initial.balance:400000.0}")
    private double initialBalance;
    
    public void init() {
        this.currentBalance = initialBalance;
    }
    
    public double getFunds() {
        return currentBalance;
    }

    public void addFunds(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Fund amount must be positive");
        }
        this.currentBalance = this.currentBalance + amount;
    }

    public LoanApplicationResult apply(Applicant applicant) {
        if (applicant == null) {
            throw new IllegalArgumentException("Applicant cannot be null");
        }
        if (applicant.getId() == null) {
            throw new IllegalArgumentException("Applicant ID cannot be null");
        }
        
        LoanApplicationResult loanApplicationResult = LoanProcessor.process(applicant);
        loanStatus.put(applicant.getId(), loanApplicationResult);
        return loanApplicationResult;
    }

    public LoanApplicationStatus processLoan(String applicantID) {
        if (applicantID == null) {
            throw new IllegalArgumentException("Applicant ID cannot be null");
        }
        
        LoanApplicationResult loanApplicationResult = loanStatus.get(applicantID);
        if (loanApplicationResult == null) {
            throw new IllegalArgumentException("No application found for applicant ID: " + applicantID);
        }
        
        if (loanApplicationResult.getApplicationStatus() == LoanApplicationStatus.QUALIFIED){
            if (loanApplicationResult.getLoanAmount() <= currentBalance){
                loanApplicationResult.setApplicationStatus(LoanApplicationStatus.APPROVED);
                currentBalance = currentBalance - loanApplicationResult.getLoanAmount();
                loanStatus.put(applicantID, loanApplicationResult);
                pendingFunds = pendingFunds + loanApplicationResult.getLoanAmount();
            } else {
                loanApplicationResult.setApplicationStatus(LoanApplicationStatus.ON_HOLD);
            }
        } else if (loanApplicationResult.getApplicationStatus() == LoanApplicationStatus.DENIED) {
            // No action needed - already denied
        }
        return loanApplicationResult.getApplicationStatus();
    }

    public double getPendingFunds() {
        return pendingFunds;
    }

    public LoanApplicationResult applicantResponse(String applicantID, LoanApplicationStatus status) {
        if (applicantID == null) {
            throw new IllegalArgumentException("Applicant ID cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        
        LoanApplicationResult loanApplicationResult = loanStatus.get(applicantID);
        if (loanApplicationResult == null) {
            throw new IllegalArgumentException("No application found for applicant ID: " + applicantID);
        }
        
        if(status == LoanApplicationStatus.ACCEPTED){
            pendingFunds = pendingFunds - loanApplicationResult.getLoanAmount();
            loanApplicationResult.setApplicationStatus(status);
            loanStatus.put(applicantID, loanApplicationResult);
        } else {
            currentBalance = currentBalance + loanApplicationResult.getLoanAmount();
            pendingFunds = pendingFunds - loanApplicationResult.getLoanAmount();
            loanApplicationResult.setApplicationStatus(status);
            loanStatus.put(applicantID, loanApplicationResult);
        }
        return loanStatus.get(applicantID);
    }

    public void expiredLoan() {
        LocalDate currentDate = LocalDate.now();
        for (Map.Entry<String, LoanApplicationResult> entry : loanStatus.entrySet()) {
            LoanApplicationResult loanApplicationResult = entry.getValue();
            if (loanApplicationResult.getApplicant() == null || loanApplicationResult.getApplicant().getDate() == null) {
                continue; // Skip if applicant or date is null
            }
            
            long days = loanApplicationResult.getApplicant().getDate().until(currentDate, ChronoUnit.DAYS);

            if (loanApplicationResult.getApplicationStatus() == LoanApplicationStatus.APPROVED && days > 3) {
               loanApplicationResult.setApplicationStatus(LoanApplicationStatus.EXPIRED);
               loanStatus.put(entry.getKey(), loanApplicationResult);
               currentBalance = currentBalance + loanApplicationResult.getLoanAmount();
               pendingFunds = pendingFunds - loanApplicationResult.getLoanAmount();
            }
        }

    }

    public LoanApplicationStatus getApplicationStatus(String applicantID) {
        if (applicantID == null) {
            throw new IllegalArgumentException("Applicant ID cannot be null");
        }
        LoanApplicationResult result = loanStatus.get(applicantID);
        if (result == null) {
            throw new IllegalArgumentException("No application found for applicant ID: " + applicantID);
        }
        return result.getApplicationStatus();
    }

    public List<LoanApplicationResult> searchByStatus(LoanApplicationStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        return loanStatus
                .values()
                .stream()
                .filter(e -> e.getApplicationStatus() == status)
                .collect(Collectors.toList());
    }
}

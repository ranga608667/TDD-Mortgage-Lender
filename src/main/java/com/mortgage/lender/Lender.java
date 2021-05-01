package com.mortgage.lender;

import java.sql.SQLOutput;
import java.util.HashMap;

public class Lender {
    private double currentBalance;
    private double pendingFunds;
    private HashMap<String,LoanApplicationResult> loanStatus = new HashMap<>();
    public Lender (double currentBalance){
        this.currentBalance=currentBalance;
    }
    public double getFunds() {
        return currentBalance;
    }

    public void addFunds(double amount) {
        if (amount <= 0) {
            System.out.println("No Fund is added");
            return;
        }
        currentBalance = currentBalance + amount;
    }

    public LoanApplicationResult apply(Applicant applicant) {
        LoanApplicationResult loanApplicationResult= LoanProcessor.process(applicant);
        loanStatus.put(applicant.getId(), loanApplicationResult );
        return loanApplicationResult;
    }

    public LoanApplicationStatus processLoan(String applicantID) {
        LoanApplicationResult loanApplicationResult=loanStatus.get(applicantID);
        if (loanApplicationResult.getApplicationStatus() == LoanApplicationStatus.QUALIFIED){
            if (loanApplicationResult.getLoanAmount() <= currentBalance){
                loanApplicationResult.setApplicationStatus(LoanApplicationStatus.APPROVED);
                currentBalance=currentBalance-loanApplicationResult.getLoanAmount();
                loanStatus.put(applicantID, loanApplicationResult);
                pendingFunds=pendingFunds+loanApplicationResult.getLoanAmount();
            } else
                loanApplicationResult.setApplicationStatus(LoanApplicationStatus.ON_HOLD);
        } else if (loanApplicationResult.getApplicationStatus() == LoanApplicationStatus.DENIED ) {
            System.out.println("Application you are trying to approve is Not Qualified");
        }
        return loanApplicationResult.getApplicationStatus();
    }

    public double getPendingFunds() {
        return pendingFunds;
    }

    public LoanApplicationResult applicantResponse(String applicantID, LoanApplicationStatus status) {
        LoanApplicationResult loanApplicationResult = loanStatus.get(applicantID);
        if(status == LoanApplicationStatus.ACCEPTED){
            pendingFunds = pendingFunds - loanApplicationResult.getLoanAmount();
            loanApplicationResult.setApplicationStatus(status);
            loanStatus.put(applicantID, loanApplicationResult);
        } else{
            currentBalance = currentBalance + loanApplicationResult.getLoanAmount();
            pendingFunds =  pendingFunds - loanApplicationResult.getLoanAmount();
            loanApplicationResult.setApplicationStatus(status);
            loanStatus.put(applicantID, loanApplicationResult);
        }
        return loanStatus.get(applicantID);
    }

}

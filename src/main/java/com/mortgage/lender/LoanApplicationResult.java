package com.mortgage.lender;

import java.util.Objects;

public class LoanApplicationResult {
    private String qualification;
    private double loanAmount;
    private LoanApplicationStatus applicationStatus;
    private Applicant applicant;

    public LoanApplicationResult(String qualification, double loanAmount, LoanApplicationStatus applicationStatus, Applicant applicant) {
        this.setQualification(qualification);
        this.setLoanAmount(loanAmount);
        this.setApplicationStatus(applicationStatus);
        this.setApplicant(applicant);
    }


    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public LoanApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(LoanApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public boolean equals(Object o) {
        LoanApplicationResult loanApplicationResult = (LoanApplicationResult) o;
        return Objects.equals(qualification, loanApplicationResult.qualification) &&
                Objects.equals(loanAmount, loanApplicationResult.loanAmount) &&
                Objects.equals(applicationStatus, loanApplicationResult.applicationStatus);
    }

}

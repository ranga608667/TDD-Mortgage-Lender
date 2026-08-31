package com.mortgage.lender.dto;

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
        if (loanAmount < 0) {
            throw new IllegalArgumentException("Loan amount cannot be negative");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanApplicationResult that = (LoanApplicationResult) o;
        return Objects.equals(qualification, that.qualification) &&
                Objects.equals(loanAmount, that.loanAmount) &&
                Objects.equals(applicationStatus, that.applicationStatus) &&
                Objects.equals(applicant, that.applicant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualification, loanAmount, applicationStatus, applicant);
    }
}
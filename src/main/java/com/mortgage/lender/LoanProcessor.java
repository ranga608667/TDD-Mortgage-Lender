package com.mortgage.lender;

public class LoanProcessor {
    public static final String QUALIFIED = "qualified";
    public static final String PARTIALLY_QUALIFIED = "partially qualified";
    public static final String DENIED = "denied";

    public static LoanApplicationResult process(Applicant applicant) {
        double savingsPercentage = (applicant.getSavings() / applicant.getRequestedAmount()) * 100;
        if (applicant.getDtl() < 36 && applicant.getCreditScore() > 620) {
            if (savingsPercentage >= 25) {
                return new LoanApplicationResult(QUALIFIED, applicant.getRequestedAmount(), LoanApplicationStatus.QUALIFIED, applicant);
            } else {
                double loanAmount = 4 * applicant.getSavings();
                return new LoanApplicationResult(PARTIALLY_QUALIFIED, loanAmount, LoanApplicationStatus.QUALIFIED, applicant);
            }
        } else {
            return new LoanApplicationResult(DENIED, 0, LoanApplicationStatus.DENIED, applicant);
        }
    }
}

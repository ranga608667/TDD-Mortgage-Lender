package com.mortgage.lender;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageLenderTestCase {

    Lender lender;

    @BeforeEach
    void setUpBeforeClass() throws Exception {
        this.lender = new Lender(300000);
    }

    @Test
    void checkAvailableFunds() {
        assertEquals(300000.00, lender.getFunds());
    }

    @Test
    void depositFunds() {
        lender.addFunds(0);
        assertEquals(300000.00, lender.getFunds());
    }

    @Test
    void applyForLoan() {
        Applicant applicant = new Applicant("ID001",250000, 21, 700, 100000);
        LoanApplicationResult expectedLoanApplicationResult = new LoanApplicationResult(LoanProcessor.QUALIFIED, applicant.getRequestedAmount(), LoanApplicationStatus.QUALIFIED, applicant);
        LoanApplicationResult actualResult = lender.apply(applicant);
        assertEquals(expectedLoanApplicationResult.getQualification(), actualResult.getQualification());
        assertEquals(expectedLoanApplicationResult.getLoanAmount(), actualResult.getLoanAmount());
        assertEquals(expectedLoanApplicationResult.getApplicationStatus(), actualResult.getApplicationStatus());
        assertEquals(expectedLoanApplicationResult, actualResult);

        Applicant applicant2 = new Applicant("ID002",250000, 21, 700, 25000);
        LoanApplicationResult expectedLoanApplicationResult2 = new LoanApplicationResult(LoanProcessor.PARTIALLY_QUALIFIED, applicant2.getSavings() * 4, LoanApplicationStatus.QUALIFIED, applicant2);
        LoanApplicationResult actualResult2 = lender.apply(applicant2);
        assertEquals(expectedLoanApplicationResult2.getQualification(), actualResult2.getQualification());
        assertEquals(expectedLoanApplicationResult2.getLoanAmount(), actualResult2.getLoanAmount());
        assertEquals(expectedLoanApplicationResult2.getApplicationStatus(), actualResult2.getApplicationStatus());
        assertEquals(expectedLoanApplicationResult2, actualResult2);


        Applicant applicant3 = new Applicant("ID003", 250000, 37, 700, 25000);
        LoanApplicationResult expectedLoanApplicationResult3 = new LoanApplicationResult(LoanProcessor.DENIED, 0, LoanApplicationStatus.DENIED, applicant3);
        LoanApplicationResult actualResult3 = lender.apply(applicant3);
        assertEquals(expectedLoanApplicationResult3.getQualification(), actualResult3.getQualification());
        assertEquals(expectedLoanApplicationResult3.getLoanAmount(), actualResult3.getLoanAmount());
        assertEquals(expectedLoanApplicationResult3.getApplicationStatus(), actualResult3.getApplicationStatus());
        assertEquals(expectedLoanApplicationResult3, actualResult3);

        Applicant applicant4 = new Applicant("ID004",250000, 21, 600, 25000);
        LoanApplicationResult expectedLoanApplicationResult4 = new LoanApplicationResult(LoanProcessor.DENIED, 0, LoanApplicationStatus.DENIED, applicant4);
        LoanApplicationResult actualResult4 = lender.apply(applicant4);
        assertEquals(expectedLoanApplicationResult4.getQualification(), actualResult4.getQualification());
        assertEquals(expectedLoanApplicationResult4.getLoanAmount(), actualResult4.getLoanAmount());
        assertEquals(expectedLoanApplicationResult4.getApplicationStatus(), actualResult4.getApplicationStatus());
        assertEquals(expectedLoanApplicationResult4, actualResult4);
    }

    @Test
    void approveLoan(){

        Applicant applicant = new Applicant("ID001",250000, 21, 700, 100000);
        Applicant applicant2 = new Applicant("ID002",250000, 21, 700, 25000);
        Applicant applicant3 = new Applicant("ID003", 250000, 37, 700, 25000);
        Applicant applicant4 = new Applicant("ID004",250000, 21, 600, 25000);
        lender.apply(applicant);
        lender.apply(applicant2);
        lender.apply(applicant3);
        lender.apply(applicant4);


        assertEquals(LoanApplicationStatus.APPROVED,lender.processLoan("ID001"));
        assertEquals(50000.00,lender.getFunds());
        assertEquals(LoanApplicationStatus.ON_HOLD,lender.processLoan("ID002"));
        assertEquals(50000.00,lender.getFunds());
        assertEquals(LoanApplicationStatus.DENIED,lender.processLoan("ID003"));
        assertEquals(50000.00,lender.getFunds());
        assertEquals(LoanApplicationStatus.DENIED,lender.processLoan("ID004"));
        assertEquals(50000.00,lender.getFunds());

    }
}

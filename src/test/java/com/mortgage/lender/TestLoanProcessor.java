package com.mortgage.lender;

import com.mortgage.lender.dto.Applicant;
import com.mortgage.lender.dto.LoanApplicationResult;
import com.mortgage.lender.dto.LoanApplicationStatus;
import com.mortgage.lender.service.Lender;
import com.mortgage.lender.service.LoanProcessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoanProcessor {

    private Lender lender;

    @BeforeEach
    void setUpBeforeClass() throws Exception {
        this.lender = new Lender(400000);
    }

    @Test
    void testLoanProcessor() {
        // Test case 1: ID001 - QUALIFIED applicant 
        Applicant applicant = new Applicant("ID001", 250000, 21, 700, 100000, LocalDate.of(2021, 05, 01));
        LoanApplicationResult expectedLoanApplicationResult = new LoanApplicationResult(LoanProcessor.QUALIFIED, applicant.getRequestedAmount(), LoanApplicationStatus.QUALIFIED, applicant);
        LoanApplicationResult actualResult = lender.apply(applicant);
        assertEquals(expectedLoanApplicationResult.getQualification(), actualResult.getQualification());
        assertEquals(expectedLoanApplicationResult.getLoanAmount(), actualResult.getLoanAmount());
        assertEquals(expectedLoanApplicationResult.getApplicationStatus(), actualResult.getApplicationStatus());

        // Test case 2: ID002 - PARTIALLY_QUALIFIED applicant
        Applicant applicant2 = new Applicant("ID002", 250000, 21, 700, 25000, LocalDate.of(2021, 05, 01));
        LoanApplicationResult expectedLoanApplicationResult2 = new LoanApplicationResult(LoanProcessor.PARTIALLY_QUALIFIED, applicant2.getSavings() * 4, LoanApplicationStatus.PARTIALLY_QUALIFIED, applicant2);
        LoanApplicationResult actualResult2 = lender.apply(applicant2);
        assertEquals(expectedLoanApplicationResult2.getQualification(), actualResult2.getQualification());
        assertEquals(expectedLoanApplicationResult2.getLoanAmount(), actualResult2.getLoanAmount());
        assertEquals(expectedLoanApplicationResult2.getApplicationStatus(), actualResult2.getApplicationStatus());

        // Test case 3: ID003 - DENIED applicant 
        Applicant applicant3 = new Applicant("ID003", 250000, 37, 700, 25000, LocalDate.of(2021, 05, 01));
        LoanApplicationResult expectedLoanApplicationResult3 = new LoanApplicationResult(LoanProcessor.DENIED, 0, LoanApplicationStatus.DENIED, applicant3);
        LoanApplicationResult actualResult3 = lender.apply(applicant3);
        assertEquals(expectedLoanApplicationResult3.getQualification(), actualResult3.getQualification());
        assertEquals(expectedLoanApplicationResult3.getLoanAmount(), actualResult3.getLoanAmount());
        assertEquals(expectedLoanApplicationResult3.getApplicationStatus(), actualResult3.getApplicationStatus());

        // Test case 4: ID004 - DENIED applicant
        Applicant applicant4 = new Applicant("ID004", 250000, 21, 619, 25000, LocalDate.of(2021, 05, 01));
        LoanApplicationResult expectedLoanApplicationResult4 = new LoanApplicationResult(LoanProcessor.DENIED, 0, LoanApplicationStatus.DENIED, applicant4);
        LoanApplicationResult actualResult4 = lender.apply(applicant4);
        assertEquals(expectedLoanApplicationResult4.getQualification(), actualResult4.getQualification());
        assertEquals(expectedLoanApplicationResult4.getLoanAmount(), actualResult4.getLoanAmount());
        assertEquals(expectedLoanApplicationResult4.getApplicationStatus(), actualResult4.getApplicationStatus());

        // Test case 5: ID005 - PARTIALLY_QUALIFIED applicant 
        Applicant applicant5 = new Applicant("ID005", 250000, 30, 700, 50000, LocalDate.of(2021, 05, 01));
        LoanApplicationResult expectedLoanApplicationResult5 = new LoanApplicationResult(LoanProcessor.PARTIALLY_QUALIFIED, applicant5.getSavings() * 4, LoanApplicationStatus.PARTIALLY_QUALIFIED, applicant5);
        LoanApplicationResult actualResult5 = lender.apply(applicant5);
        assertEquals(expectedLoanApplicationResult5.getQualification(), actualResult5.getQualification());
        assertEquals(expectedLoanApplicationResult5.getLoanAmount(), actualResult5.getLoanAmount());
        assertEquals(expectedLoanApplicationResult5.getApplicationStatus(), actualResult5.getApplicationStatus());

        // Test case 6: Process loan applications and verify fund management
        assertEquals(LoanApplicationStatus.APPROVED, lender.processLoan("ID001"));
        assertEquals(150000.00, lender.getFunds(), 0.001);
        
        assertEquals(LoanApplicationStatus.APPROVED, lender.processLoan("ID002"));
        assertEquals(50000.00, lender.getFunds(), 0.001);

        assertEquals(LoanApplicationStatus.DENIED, lender.processLoan("ID003"));
        assertEquals(50000.00, lender.getFunds(), 0.001);

        assertEquals(LoanApplicationStatus.DENIED, lender.processLoan("ID004"));
        assertEquals(50000.00, lender.getFunds(), 0.001);

        assertEquals(LoanApplicationStatus.APPROVED, lender.processLoan("ID005"));
        assertEquals(50000.00, lender.getFunds(), 0.001);
        assertEquals(50000.00, lender.getFunds(), 0.001);
    }
}
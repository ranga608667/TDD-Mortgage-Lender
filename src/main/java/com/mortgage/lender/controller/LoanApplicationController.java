package com.mortgage.lender.controller;

import com.mortgage.lender.service.Lender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanApplicationController {

    @Autowired
    private Lender lender;

    @PostMapping("/apply")
    public ResponseEntity<com.mortgage.lender.dto.LoanApplicationResult> applyForLoan(@RequestBody LoanApplicationRequest request) {
        com.mortgage.lender.dto.Applicant applicant = new com.mortgage.lender.dto.Applicant(
            request.getApplicantId(),
            request.getRequestedAmount(),
            request.getDebtToIncomeRatio(),
            request.getCreditScore(),
            request.getSavings(),
            LocalDate.now()
        );

        com.mortgage.lender.dto.LoanApplicationResult result = lender.apply(applicant);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/status/{applicantId}")
    public ResponseEntity<com.mortgage.lender.dto.LoanApplicationStatus> getApplicationStatus(@PathVariable String applicantId) {
        com.mortgage.lender.dto.LoanApplicationStatus status = lender.getApplicationStatus(applicantId);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/process/{applicantId}")
    public ResponseEntity<com.mortgage.lender.dto.LoanApplicationStatus> processLoan(@PathVariable String applicantId) {
        com.mortgage.lender.dto.LoanApplicationStatus status = lender.getApplicationStatus(applicantId);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/response/{applicantId}")
    public ResponseEntity<com.mortgage.lender.dto.LoanApplicationResult> handleApplicantResponse(
            @PathVariable String applicantId,
            @RequestBody ApplicantResponseRequest request) {
        com.mortgage.lender.dto.LoanApplicationResult result = lender.applicantResponse(applicantId, request.getStatus());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/funds")
    public ResponseEntity<FundsInfo> getFunds() {
        FundsInfo fundsInfo = new FundsInfo(lender.getFunds(), lender.getPendingFunds());
        return ResponseEntity.ok(fundsInfo);
    }

    @PostMapping("/add-funds")
    public ResponseEntity<String> addFunds(@RequestBody AddFundsRequest request) {
        lender.addFunds(request.getAmount());
        return ResponseEntity.ok("Funds added successfully");
    }

    @GetMapping("/search/{status}")
    public ResponseEntity<List<com.mortgage.lender.dto.LoanApplicationResult>> searchByStatus(@PathVariable com.mortgage.lender.dto.LoanApplicationStatus status) {
        List<com.mortgage.lender.dto.LoanApplicationResult> results = lender.searchByStatus(status);
        return ResponseEntity.ok(results);
    }

    // Helper classes for request/response bodies
    public static class LoanApplicationRequest {
        private String applicantId;
        private double requestedAmount;
        private int debtToIncomeRatio;
        private int creditScore;
        private double savings;

        // Getters and setters
        public String getApplicantId() { return applicantId; }
        public void setApplicantId(String applicantId) { this.applicantId = applicantId; }

        public double getRequestedAmount() { return requestedAmount; }
        public void setRequestedAmount(double requestedAmount) { this.requestedAmount = requestedAmount; }

        public int getDebtToIncomeRatio() { return debtToIncomeRatio; }
        public void setDebtToIncomeRatio(int debtToIncomeRatio) { this.debtToIncomeRatio = debtToIncomeRatio; }

        public int getCreditScore() { return creditScore; }
        public void setCreditScore(int creditScore) { this.creditScore = creditScore; }

        public double getSavings() { return savings; }
        public void setSavings(double savings) { this.savings = savings; }
    }

    public static class ApplicantResponseRequest {
        private com.mortgage.lender.dto.LoanApplicationStatus status;

        // Getters and setters
        public com.mortgage.lender.dto.LoanApplicationStatus getStatus() { return status; }
        public void setStatus(com.mortgage.lender.dto.LoanApplicationStatus status) { this.status = status; }
    }

    public static class AddFundsRequest {
        private double amount;

        // Getters and setters
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
    }

    public static class FundsInfo {
        private double availableFunds;
        private double pendingFunds;

        public FundsInfo(double availableFunds, double pendingFunds) {
            this.availableFunds = availableFunds;
            this.pendingFunds = pendingFunds;
        }

        // Getters and setters
        public double getAvailableFunds() { return availableFunds; }
        public void setAvailableFunds(double availableFunds) { this.availableFunds = availableFunds; }

        public double getPendingFunds() { return pendingFunds; }
        public void setPendingFunds(double pendingFunds) { this.pendingFunds = pendingFunds; }
    }
}
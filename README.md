# TDD-Mortgage-Lender

## Problem Statement

This project addresses the challenge of managing mortgage lending operations in a simplified but realistic simulation. The system solves the core problem of how a mortgage lender should evaluate, approve, and manage loan applications while maintaining financial stability.

## Solution Overview

The TDD-Mortgage-Lender application provides a complete simulation of a mortgage lending process that handles:
- Loan qualification based on applicant financial metrics (DTI ratio, credit score, savings)
- Fund management with separate tracking of available and pending funds
- Complete loan lifecycle management from application to final disposition
- Risk management through automatic fund allocation and expiration handling

## Key Features

### 1. Financial Management
- Track available funds for lending operations
- Add money to the lender's funding pool
- Automatic fund transfer between available and pending accounts

### 2. Loan Qualification Engine
- Evaluate applicants based on industry-standard criteria:
  - Debt-to-Income (DTI) ratio below 36%
  - Credit score above 620
  - Savings amount at least 25% of requested loan
- Three qualification levels: Qualified, Partially Qualified, and Denied

### 3. Loan Lifecycle Management
- Process loan applications from initial submission to final disposition
- Handle applicant responses (accept/reject) with proper fund adjustments
- Automatic loan expiration after 3 days for unresponded approved loans
- Comprehensive status tracking throughout the loan process

### 4. Risk Mitigation
- Prevent over-extending funds by checking availability before approval
- Separate pending funds account to track commitments
- Automatic recovery of funds from expired loans
- Clear audit trail of all financial transactions

## Core Functionality Implemented

The system implements a complete mortgage lending workflow that addresses real-world lender challenges:
- **Qualification Process**: Systematically evaluates applicants based on financial metrics
- **Fund Allocation**: Ensures sufficient funds are available before loan approval
- **Status Management**: Tracks loans through all possible states (qualified, approved, accepted, rejected, expired)
- **Financial Control**: Maintains proper accounting between available and pending funds

## Technical Approach

This project demonstrates Test-Driven Development (TDD) principles with:
- Comprehensive test coverage for all business logic
- Object-Oriented Programming principles applied appropriately
- Spring Boot framework for dependency injection and application structure
- Clean, maintainable code that follows SOLID principles

The implementation provides a realistic simulation of how mortgage lenders manage their loan portfolios while ensuring financial stability and proper risk management.

## Rich Examples

### Example 1: Loan Qualification Scenarios

| Requested Amount | DTI | Credit Score | Savings | Qualification Status | Loan Amount | Result |
|------------------|-----|--------------|---------|---------------------|-------------|--------|
| $250,000         | 21  | 700          | $100,000| Qualified           | $250,000    | Approved |
| $250,000         | 37  | 700          | $100,000| Denied              | $0          | Rejected |
| $250,000         | 30  | 600          | $100,000| Denied              | $0          | Rejected |
| $250,000         | 30  | 700          | $50,000 | Partially Qualified | $200,000    | Approved |

### Example 2: Fund Management Operations

| Operation | Available Funds | Pending Funds | Action Taken |
|-----------|-----------------|---------------|--------------|
| Initial Setup | $400,000 | $0 | System initialized |
| Add Funds | $400,000 | $0 | Added $100,000 |
| Apply for Loan | $500,000 | $0 | Requested $250,000 loan |
| Loan Approved | $250,000 | $250,000 | Funds transferred to pending |
| Loan Accepted | $250,000 | $0 | Pending funds released |
| Loan Rejected | $250,000 | $0 | Pending funds returned |

### Example 3: Loan Lifecycle Management

| Status | Description | Funds Impact | Time Period |
|--------|-------------|--------------|-------------|
| Qualified | Applicant meets basic criteria | No change | Instant |
| Approved | Loan amount confirmed and available | Available funds reduced, pending funds increased | Instant |
| Accepted | Borrower accepts offer | Pending funds moved to available | Instant |
| Rejected | Borrower rejects offer | Pending funds returned to available | Instant |
| Expired | 3-day window passes without response | Pending funds returned to available | 3 days |

### Example 4: Risk Management Scenarios

| Scenario | Available Funds | Loan Request | Result |
|----------|-----------------|--------------|--------|
| Sufficient Funds | $300,000 | $200,000 | Approved |
| Insufficient Funds | $150,000 | $200,000 | On Hold |
| Overdraft Risk | $0 | $100,000 | Denied |
| Partial Qualification | $300,000 | $150,000 | Approved |

## Usage Examples

### Starting the Application
```bash
./gradlew bootRun
```
The application starts on port 8081 with default balance of $400,000.

### Key Endpoints
- `GET /funds` - Check available funds
- `POST /funds/deposit` - Add money to funding pool  
- `POST /loan/apply` - Submit loan application
- `GET /loan/status/{id}` - Check loan status
- `POST /loan/respond` - Accept/reject loan offer

## Testing
All 14 tests pass with clean build execution. The system uses comprehensive test coverage to ensure all business logic functions correctly.
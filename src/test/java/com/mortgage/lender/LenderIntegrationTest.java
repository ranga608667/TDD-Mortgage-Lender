package com.mortgage.lender;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LenderIntegrationTest {

    @Test
    public void testLenderConstructorWithBalance() {
        // Test that we can create a Lender with initial balance
        Lender lender = new Lender(400000);
        assertEquals(400000.0, lender.getFunds());
    }
    
    @Test
    public void testLenderDefaultConstructor() {
        // Test that we can create a Lender with default constructor
        Lender lender = new Lender();
        assertEquals(0.0, lender.getFunds());
    }
    
    @Test
    public void testAddFunds() {
        Lender lender = new Lender(400000);
        lender.addFunds(100000);
        assertEquals(500000.0, lender.getFunds());
    }
}
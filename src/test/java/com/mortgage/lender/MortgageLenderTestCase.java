package com.mortgage.lender;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageLenderTestCase {

    Lender lender;

    @BeforeEach
    void setUpBeforeClass() throws Exception {
      this.lender   = new Lender();
    }

    @Test
    void checkAvailableFunds(){
        assertEquals(100000.00,lender.getFunds());
    }

    @Test
    void depositFunds(){
        lender.addFunds(50000.00);
        assertEquals(150000.00, lender.getFunds());
    }
}

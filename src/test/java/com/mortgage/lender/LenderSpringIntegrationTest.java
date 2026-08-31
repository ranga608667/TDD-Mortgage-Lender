package com.mortgage.lender;

import com.mortgage.lender.service.Lender;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LenderSpringIntegrationTest {

    @Autowired
    private Lender lender;

    @Test
    public void testLenderIsAutowired() {
        assertNotNull(lender);
        // Test that the default constructor works (should have initial balance from config)
        assertTrue(lender.getFunds() >= 0);
    }
}
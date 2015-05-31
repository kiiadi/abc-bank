package com.abc.account;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InterestCalculatorTest {

    private static final double DOUBLE_DELTA = 1.0e-15;

    @Test
    public void calculateInterest() {
        InterestCalculator calculator = new InterestCalculator(1.0);
        assertEquals(1.0, calculator.calculate(100), DOUBLE_DELTA);
    }
}
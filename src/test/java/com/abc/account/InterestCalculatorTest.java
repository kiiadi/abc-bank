package com.abc.account;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class InterestCalculatorTest {

    @Test
    public void calculateInterest() {
        InterestCalculator calculator = new InterestCalculator(1.0);
        assertThat(calculator.calculate(100), equalTo(1.0));
    }
}
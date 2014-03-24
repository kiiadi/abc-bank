package com.abc.interest;


import com.abc.AccountType;
import com.abc.TestUtils;
import com.abc.Transaction;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static com.abc.Constants.ONE_THOUSAND_TH;
import static com.abc.Constants.THOUSAND;
import static com.abc.Constants.TWO_THOUSAND_TH;

public class SavingsInterestStrategyTest {

    @Test
    public void test() {
        InterestStrategyFactory factory = InterestStrategyFactory.getInstance();
        InterestStrategy strategy = factory.getStrategy(AccountType.Savings);

        TestUtils.assertEquals(BigDecimal.ZERO, strategy.interestEarned(BigDecimal.ZERO, Collections.<Transaction>emptyList()));
        TestUtils.assertEquals(BigDecimal.ONE.multiply(ONE_THOUSAND_TH), strategy.interestEarned(BigDecimal.ONE, Collections.<Transaction>emptyList()));
        TestUtils.assertEquals(THOUSAND.multiply(ONE_THOUSAND_TH), strategy.interestEarned(THOUSAND, Collections.<Transaction>emptyList()));
        TestUtils.assertEquals(THOUSAND.multiply(ONE_THOUSAND_TH)
                .add(BigDecimal.ONE.multiply(TWO_THOUSAND_TH)), strategy.interestEarned(new BigDecimal(1001), Collections.<Transaction>emptyList()));
    }
}


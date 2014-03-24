package com.abc.interest;


import com.abc.AccountType;
import com.abc.Constants;
import com.abc.TestUtils;
import com.abc.Transaction;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;

public class CheckingInterestStrategyTest {

    @Test
    public void test() {
        InterestStrategyFactory factory = InterestStrategyFactory.getInstance();
        InterestStrategy strategy = factory.getStrategy(AccountType.Checking);

        TestUtils.assertEquals(BigDecimal.ZERO, strategy.interestEarned(BigDecimal.ZERO, Collections.<Transaction>emptyList()));
        TestUtils.assertEquals(BigDecimal.ONE.multiply(Constants.ONE_THOUSAND_TH), strategy.interestEarned(BigDecimal.ONE, Collections.<Transaction>emptyList()));
    }
}


package com.abc.interest;


import com.abc.AccountType;
import com.abc.TestUtils;
import com.abc.Transaction;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.abc.Constants.*;

public class MaxiSavingsInterestStrategyTest {

    @Test
    public void testNoWithdrawals() {
        test(Collections.<Transaction>emptyList(), FIVE_HUNDRED_TH);
    }

    @Test
    public void testWithdrawalInLast10Days() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(BigDecimal.ONE.negate()));
        test(transactions, ONE_THOUSAND_TH);
    }

    private void test(List<Transaction> transactions, BigDecimal expectedRate) {
        InterestStrategyFactory factory = InterestStrategyFactory.getInstance();
        InterestStrategy strategy = factory.getStrategy(AccountType.MaxiSavings);

        TestUtils.assertEquals(BigDecimal.ZERO, strategy.interestEarned(BigDecimal.ZERO, transactions));
        TestUtils.assertEquals(BigDecimal.ONE.multiply(expectedRate), strategy.interestEarned(BigDecimal.ONE, transactions));
        TestUtils.assertEquals(new BigDecimal(1000).multiply(expectedRate), strategy.interestEarned(new BigDecimal(1000), transactions));
        TestUtils.assertEquals(new BigDecimal(2000).multiply(expectedRate), strategy.interestEarned(new BigDecimal(2000), transactions));
        TestUtils.assertEquals(new BigDecimal(10001).multiply(expectedRate), strategy.interestEarned(new BigDecimal(10001), transactions));
    }

}


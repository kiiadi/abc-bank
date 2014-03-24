package com.abc.interest;


import com.abc.AccountType;
import junit.framework.Assert;
import org.junit.Test;

public class InterestStrategyFactoryTest {

    @Test
    public void testFactory() {
        InterestStrategyFactory factory = InterestStrategyFactory.getInstance();
        Assert.assertEquals(CheckingInterestStrategy.class, factory.getStrategy(AccountType.Checking).getClass());
        Assert.assertEquals(SavingsInterestStrategy.class, factory.getStrategy(AccountType.Savings).getClass());
        Assert.assertEquals(MaxiSavingsInterestStrategy.class, factory.getStrategy(AccountType.MaxiSavings).getClass());
    }
}

package com.abc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountTest.class, AccountTypeTest.class, BankTest.class,
		CustomerTest.class, InterestRateFactoryTest.class,
		InterestRateTest.class, TransactionTest.class,
		TransactionTypeTest.class, UtilityTest.class })
public class AllTests {

}

package com.abc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DateProviderTest.class, TransactionTest.class, AccountTest.class, CustomerTest.class, BankTest.class })
public class AllTests {

}

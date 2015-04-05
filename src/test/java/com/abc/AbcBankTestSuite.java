package com.abc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BankTest.class,
	MaxiSavingInterestCalculatorTest.class,SavingInterestCalculatorTest.class, CheckingInterestCalculatorTest.class, CustomerTest.class,TransactionTest.class,AccountTest.class})
public class AbcBankTestSuite {

}

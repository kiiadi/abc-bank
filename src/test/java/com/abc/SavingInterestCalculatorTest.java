package com.abc;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.abc.BankConstants.AccountType;

public class SavingInterestCalculatorTest {
	private SavingInterestCalculator interestCalculator  = new SavingInterestCalculator(); 
	@Test
	public void testLessThanThousand()
	{
		Account savingAccount = new Account(AccountType.SAVINGS);
		savingAccount.deposit(365);
		double interest = interestCalculator.calculate(savingAccount);
		assertEquals(0.001, interest, BankTestConstants.DOUBLE_DELTA);
	}
	@Test
	public void testThousand()
	{
		Account savingAccount = new Account(AccountType.SAVINGS);
		savingAccount.deposit(1000);
		double interest = interestCalculator.calculate(savingAccount);
		assertEquals(0.0027397260273972603, interest, BankTestConstants.DOUBLE_DELTA);
	}
	@Test

	public void testMoreThanThousand()
	{
		Account savingAccount = new Account(AccountType.SAVINGS);
		savingAccount.deposit(183000);
		double interest = interestCalculator.calculate(savingAccount);
		assertEquals(1, interest, BankTestConstants.DOUBLE_DELTA);
	}

}

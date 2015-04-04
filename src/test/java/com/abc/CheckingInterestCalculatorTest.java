package com.abc;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.abc.BankConstants.AccountType;

public class CheckingInterestCalculatorTest {
	private CheckingInterestCalculator interestCalculator = new CheckingInterestCalculator();

	@Test
	public void testCheckingItnerestRate() {
		Account checkingAccount = new Account(AccountType.CHECKING);
		checkingAccount.deposit(365000);
		double interest = interestCalculator.calculate(checkingAccount);
		assertEquals(1, interest, BankTestConstants.DOUBLE_DELTA);
	}
}

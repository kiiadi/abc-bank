package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: mchernyak
 * Date: 2/19/14
 * Time: 5:04 PM
 */
public class AccountTypeTest {
	@Test
	public void testAccountTypeAsString() {
		AccountType accountTypeChecking = AccountType.CHECKING;
		AccountType accountTypeSavings = AccountType.SAVINGS;
		AccountType accountTypeMaxiSavings = AccountType.MAXI_SAVINGS;

		assertEquals("account type strings should match",
				"Checking Account", accountTypeChecking.toString());

		assertEquals("account type strings should match",
				"Savings Account", accountTypeSavings.toString());

		assertEquals("account type strings should match",
				"Maxi Savings Account", accountTypeMaxiSavings.toString());
	}
}

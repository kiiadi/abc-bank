package com.abc;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountFactoryTest {

	@Test
	public void testNewAccount() {
		Account acct;
		try {
			acct = AccountFactory.newAccount(AccountType.CHECKING);
			assert(acct instanceof CheckingAccount);
			acct = AccountFactory.newAccount(AccountType.SAVINGS);
			assert(acct instanceof SavingsAccount);
			acct = AccountFactory.newAccount(AccountType.MAXI_SAVINGS);
			assert(acct instanceof MaxiSavingsAccount);
			acct = AccountFactory.newAccount(AccountType.SUPER_SAVINGS);
			assert(acct instanceof SuperSavingsAccount);
		} catch (InvalidAccount e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

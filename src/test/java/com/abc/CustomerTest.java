package com.abc;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CustomerTest
	{

		private static final double	DOUBLE_DELTA	= 1e-15;

		@Test
		//Test customer statement generation
		public void customer_statement_test()
			{

				Account checkingAccount = new CheckingAccount();
				Account savingsAccount = new SavingsAccount();

				Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

				checkingAccount.deposit(100.0);
				savingsAccount.deposit(4000.0);
				savingsAccount.withdraw(200.0);

				assertEquals("Statement for Henry\n" + "\n" + "Checking Account\n" + "  deposit $100.00\n"
								+ "Total $100.00\n" + "\n" + "Savings Account\n" + "  deposit $4,000.00\n"
								+ "  withdrawal $200.00\n" + "Total $3,800.00\n" + "\n"
								+ "Total In All Accounts $3,900.00", henry.getStatement());
			}

		@Test
		public void multiple_accounts_test()
			{
				Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount());
				oscar.openAccount(new CheckingAccount());
				oscar.openAccount(new MaxiSavingsAccount());
				assertEquals(3, oscar.getNumberOfAccounts());
			}

		@Test
		public void transer_test()
			{
				Bank bank = new Bank();
				Account checkingAccount = new CheckingAccount();
				Account savingsAccount = new SavingsAccount();
				Customer bill = new Customer("Bill").openAccount(checkingAccount);
				bill.openAccount(savingsAccount);

				bank.addCustomer(bill);

				savingsAccount.deposit(3000.0);

				savingsAccount.transfer(checkingAccount, 1000);

				assertEquals(2000, savingsAccount.getBalance(true), DOUBLE_DELTA);
				assertEquals(1000, checkingAccount.getBalance(true), DOUBLE_DELTA);

			}

		@Test
		public void maxi_withdraw_date_test()
			{
				MaxiSavingsAccount account = new MaxiSavingsAccount();

				Customer henry = new Customer("Henry").openAccount(account);

				account.deposit(1000.0);
				account.withdraw(200.0);
				
				assertEquals(800.00, account.getBalance(true), DOUBLE_DELTA);
				
				assertEquals(Utils.now(), account.getLastWithdrawDate() );
				
			}	
		
		
		@Test
		public void maxi_calc_interest_test()
			{
				Account account = new MaxiSavingsAccount();

				Customer henry = new Customer("Henry").openAccount(account);

				account.deposit(500.0);
				account.deposit(500.0);
				
				assertEquals(1000.00, account.getBalance(true), DOUBLE_DELTA);
				assertEquals(50.00, account.calculateInterest(), DOUBLE_DELTA);

				account.withdraw(200);
				assertEquals(800.00, account.getBalance(true), DOUBLE_DELTA);
				assertEquals(0.80, account.calculateInterest(), DOUBLE_DELTA);
				
				
			}
		
		@Test(expected=IllegalArgumentException.class)
		public void withraw_too_much_test()
			{

				Account account = new MaxiSavingsAccount();

				Customer henry = new Customer("Henry").openAccount(account);

				account.deposit(1000.0);

				account.withdraw(1000.01);
			}

	
	}

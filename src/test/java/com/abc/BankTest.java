package com.abc;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BankTest
	{

		private static final double	DOUBLE_DELTA	= 1e-15;

		@Test
		public void customerSummary()
			{
				Bank bank = new Bank();
				Customer john = new Customer("John");
				john.openAccount(new CheckingAccount());
				bank.addCustomer(john);

				assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
			}

		@Test
		public void checking_interest_test()
			{
				Bank bank = new Bank();
				Account checkingAccount = new CheckingAccount();
				Customer bill = new Customer("Bill").openAccount(checkingAccount);
				bank.addCustomer(bill);

				checkingAccount.deposit(100.0);

				assertEquals(1.00, bank.totalInterestPaid(), DOUBLE_DELTA);
			}

		@Test
		public void savings_interest_test()
			{
				Bank bank = new Bank();
				Account savingsAccount = new SavingsAccount();
				bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

				savingsAccount.deposit(1500.0);

				assertEquals(20.00, bank.totalInterestPaid(), DOUBLE_DELTA);
			}

		@Test
		public void maxi_interest_test()
			{
				Bank bank = new Bank();
				Account maxiAccount = new MaxiSavingsAccount();
				bank.addCustomer(new Customer("Bill").openAccount(maxiAccount));

				maxiAccount.deposit(3000.0);

				assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
			}

		@Test
		public void periodic_rate_test()
			{
				double annual_rate = 0.12;
				
				double periodic_rate = Utils.getPeriodicRate(annual_rate, 365 );
				
				assertEquals( 0.00032877, periodic_rate, DOUBLE_DELTA );
			}
		
		@Test 
		public void future_value_test()
			{
				double annual_rate = 0.12;
				
				double periodic_rate = Utils.getPeriodicRate(annual_rate, 365 );
				
				double fv = Utils.futureValue(1000.00, periodic_rate, 6);
				assertEquals( 1001.9742, fv, DOUBLE_DELTA );				
			}
		
		@Test
		public	void transer_test()
			{
				Bank bank = new Bank();
				Account checkingAccount = new CheckingAccount();
				Account savingsAccount = new SavingsAccount();
				Customer bill = new Customer("Bill").openAccount(checkingAccount);
				bill.openAccount(savingsAccount);
				
				bank.addCustomer(bill);
				
				savingsAccount.deposit(3000.0);
				
				savingsAccount.transfer(checkingAccount, 1000 );
				
				assertEquals(2000, savingsAccount.getBalance(true), DOUBLE_DELTA );
				assertEquals(1000, checkingAccount.getBalance(true), DOUBLE_DELTA);
				
			}

	}

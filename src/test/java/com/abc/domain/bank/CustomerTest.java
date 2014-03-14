package com.abc.domain.bank;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.abc.domain.account.Account;
import com.abc.domain.account.CheckingAccount;
import com.abc.domain.account.SavingsAccount;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.TimePoint;
import com.abc.domain.sub.time.WorldClock;

public class CustomerTest {
	
	@Test
	public void testEquality() throws Exception {
	    Customer one = new Customer("Oscar");
	    Customer another = new Customer("Oscar");
	    
	    assertThat(one, is(another));
	}
	
	@Test
	public void testGetNumberOfAccounts() throws Exception {
		Customer customer = new Customer("Oscar");
		assertThat(customer.getNumberOfAccounts(), is(0));
		
		customer.openAccount(new CheckingAccount());
		assertThat(customer.getNumberOfAccounts(), is(1));
		
		customer.openAccount(new SavingsAccount());
		assertThat(customer.getNumberOfAccounts(), is(2));
	}
	
	@Test
	public void testTotalInterest() throws Exception {
		Customer customer = new Customer("Oscar");
		
		Account mockAccount1 = mock(Account.class);
		when(mockAccount1.interest()).thenReturn(Money.dollars(11.5));

		Account mockAccount2 = mock(Account.class);
		when(mockAccount2.interest()).thenReturn(Money.dollars(1.2));
		
		customer.openAccount(mockAccount1).openAccount(mockAccount2);
		
		assertThat(customer.totalInterest(), is(Money.dollars(12.7)));
	}
	
	@Test
	public void testTotaBalance() throws Exception {
		Customer customer = new Customer("Oscar");
		
		Account mockAccount1 = mock(Account.class);
		when(mockAccount1.balance()).thenReturn(Money.dollars(100.5));

		Account mockAccount2 = mock(Account.class);
		when(mockAccount2.balance()).thenReturn(Money.dollars(200.5));
		
		customer.openAccount(mockAccount1).openAccount(mockAccount2);
		
		assertThat(customer.totalBalance(), is(Money.dollars(301)));
	}
	
	@Test
	public void testShowStatement() {
		Account checking = new CheckingAccount();
		checking.deposit(Money.dollars(100.0), TimePoint.now());

		Account saving = new SavingsAccount();
		saving.deposit(Money.dollars(4000.0), TimePoint.now());
		saving.withdraw(Money.dollars(200.0), TimePoint.now());

		Customer henry = new Customer("Henry").openAccount(checking).openAccount(saving);

		String expected = "Statement for Henry\n\n" + 
						  "Checking Account\n" + 
						  " deposit $100.00\n" + 
						  "Total $100.00\n\n" + 
						  "Savings Account\n" + 
						  " deposit $4,000.00\n" + 
						  " withdrawal $200.00\n" + 
						  "Total $3,800.00\n\n" + 
						  "Total In All Accounts $3,900.00";
		
		assertThat(henry.showStatement(), is(expected));
	}
	
	@Test
	public void testTransfer() throws Exception {
		Account checking = new CheckingAccount();
		checking.deposit(Money.dollars(100.0), TimePoint.now());

		Account saving = new SavingsAccount();
		saving.deposit(Money.dollars(4000.0), TimePoint.now());

		Customer henry = new Customer("Henry").openAccount(checking).openAccount(saving);
		henry.transfer(Money.dollars(20), checking, saving, new WorldClock());
		
		assertThat(checking.balance(), is(Money.dollars(80)));
		assertThat(saving.balance(), is(Money.dollars(4020)));
	}
}

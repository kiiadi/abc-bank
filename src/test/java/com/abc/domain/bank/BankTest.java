package com.abc.domain.bank;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.abc.domain.account.Account;
import com.abc.domain.account.CheckingAccount;
import com.abc.domain.account.SavingsAccount;
import com.abc.domain.sub.money.Money;

public class BankTest {

	Bank bank;
	
	@Before
	public void setUp() throws Exception {
		bank = new Bank();
	}
	
	@Test
    public void testOpenAccount() throws Exception
    {
	    Customer bill = new Customer("Bill");
        bank.openAccount(bill, new CheckingAccount());
        
        assertThat(bank.customers().size(), is(1));
        assertThat(bill.allAccounts().size(), is(1));

        bank.openAccount(bill, new SavingsAccount());
        assertThat(bank.customers().size(), is(1));
        assertThat(bill.allAccounts().size(), is(2));
    }
	
	@Test
	public void testTotalInterestPaidWithOneCustomer() {
		Account mockAccount = mock(Account.class);
		when(mockAccount.interest()).thenReturn(Money.dollars(0.1));
		
		bank.openAccount(new Customer("Bill"), mockAccount);

		assertThat(bank.totalInterestPaid(), is(Money.dollars(0.1)));
	}

	@Test
	public void testTotalInterestPaidWithMultipleCustomers() {
		Account mockBillAccount1 = mock(Account.class);
		when(mockBillAccount1.interest()).thenReturn(Money.dollars(0.1));
		
		Account mockBillAccount2 = mock(Account.class);
		when(mockBillAccount2.interest()).thenReturn(Money.dollars(1.1));
		
		Account mockOscarAccount1 = mock(Account.class);
		when(mockOscarAccount1.interest()).thenReturn(Money.dollars(10.5));
		
		bank.openAccount(new Customer("Bill"), mockBillAccount1, mockBillAccount2);
		bank.openAccount(new Customer("Oscar"), mockOscarAccount1);

		assertThat(bank.totalInterestPaid(), is(Money.dollars(11.7)));
	}
}

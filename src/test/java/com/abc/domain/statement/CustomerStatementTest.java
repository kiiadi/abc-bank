package com.abc.domain.statement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.abc.domain.account.Account;
import com.abc.domain.account.CheckingAccount;
import com.abc.domain.account.SavingsAccount;
import com.abc.domain.bank.Customer;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.TimePoint;

public class CustomerStatementTest {

	@Test
	public void testGenerate() throws Exception {
		
		Account checking = new CheckingAccount();
		checking.deposit(Money.dollars(100.0), TimePoint.now());

		Account savings = new SavingsAccount();
		savings.deposit(Money.dollars(4000.0), TimePoint.now());
		savings.withdraw(Money.dollars(200.0), TimePoint.now());

		Customer henry = new Customer("Henry").openAccount(checking).openAccount(savings);

		Statement statement = new CustomerStatement(henry);
		
		String expected = "Statement for Henry\n\n" + 
						  "Checking Account\n" + 
						  " deposit $100.00\n" + 
						  "Total $100.00\n\n" + 
						  "Savings Account\n" + 
						  " deposit $4,000.00\n" + 
						  " withdrawal $200.00\n" + 
						  "Total $3,800.00\n\n" + 
						  "Total In All Accounts $3,900.00";
		
		assertThat(statement.generate(), is(expected));
	}
}

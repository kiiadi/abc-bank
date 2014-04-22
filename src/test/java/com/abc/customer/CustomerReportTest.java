package com.abc.customer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.abc.accounts.Account;
import com.abc.accounts.AccountFactory;
import com.abc.accounts.AccountType;
import com.abc.exceptions.InsufficientFundsException;
import com.abc.exceptions.InvalidTransactionAmountException;

/**
 * Test-cases follow the following method-name pattern:
 * methodName_Scenario_ExpectedResult()
 *
 */
public class CustomerReportTest {
	private Customer customer;
	private Account checkingAccount;
	private Account savingsAccount;
	
	@Before
	public void setUp() throws Exception {
		customer = new Customer(1, "Henry");
		
		checkingAccount = AccountFactory.createAccount(AccountType.CHECKING_ACCOUNT);
		savingsAccount = AccountFactory.createAccount(AccountType.SAVINGS_ACCOUNT);

		customer.addAccount(checkingAccount).addAccount(savingsAccount);
	}

	@Test
	public void getStatement_WhenCalled_CustomerStatementIsGenerated()
			throws InvalidTransactionAmountException, InsufficientFundsException {
		checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        
		assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", customer.getStatement());
	}

}

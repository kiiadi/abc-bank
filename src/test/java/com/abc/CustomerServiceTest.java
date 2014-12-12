package com.abc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.builder.AccountBuilder;
import com.abc.model.Account;
import com.abc.model.Customer;
import com.abc.model.Statement;
import com.abc.service.BankService;
import com.abc.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceTest {
	@Autowired
	final CustomerService service = null;
	@Autowired
	final BankService bankService = null;
	
	public CustomerServiceTest() {
		super();
	}

	@Test
	public void customer() throws Exception {
		Customer customer = service.createCustomerAndOpenAccount("Ed Erie",
				"Checking Account", AccountBuilder.CHECKING);
		// create all three accounts
		service.openAcccount(customer, "Saving Account", AccountBuilder.SAVINGS);
		assertTrue(customer.getAccounts().size() == 2);
		service.openAcccount(customer, "Max Savings",
				AccountBuilder.MAXI_SAVINGS);
		assertTrue(customer.getAccounts().size() == 3);

	}

	@Test
	public void depositFundsTest() {
		// deposit regular checking account
		try {
			Customer customer = this.bankService.getBankCustomers().get(0);
			Account account = customer.getAccounts().get(0);
			service.depositToAccount(customer, account, 1000.00);

			assertTrue(account.getTransactions().size() == 1);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}

		// deposit savings account
		try {
			Customer customer = this.bankService.getBankCustomers().get(0);
			Account account = customer.getAccounts().get(1);
			service.depositToAccount(customer, account, 2050.00);

			assertTrue(account.getTransactions().size() == 1);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}

		// deposit max account
		try {
			Customer customer = this.bankService.getBankCustomers().get(0);
			Account account = customer.getAccounts().get(2);
			service.depositToAccount(customer, account, 10000.00);

			assertTrue(account.getTransactions().size() == 1);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}

	@Test
	public void interestTest() {

		// /running test on interest of checking account
		try {
			Customer customer = this.bankService.getBankCustomers().get(0);
			Account account = customer.getAccounts().get(0);
			double amount = service.totalInterestEarned(customer, account);
			assertTrue(amount > 1000);
		} catch (Exception e) {

			e.printStackTrace();
			assertNotNull(null);
		}

		// /running test on interest of savings account
		try {
			Customer customer = this.bankService.getBankCustomers().get(0);
			Account account = customer.getAccounts().get(1);
			double amount = service.totalInterestEarned(customer, account);
			assertTrue(amount > 2050.00);
		} catch (Exception e) {

			e.printStackTrace();
			assertNotNull(null);
		}

		// /running test on interest of max savings account
		try {
			Customer customer = this.bankService.getBankCustomers().get(0);
			Account account = customer.getAccounts().get(2);
			double amount = service.totalInterestEarned(customer, account);
			assertTrue(amount > 10000.00);
		} catch (Exception e) {

			e.printStackTrace();
			assertNotNull(null);
		}

	}

	@Test
	public void statementTest() {
		try {
			Customer customer = this.bankService.getBankCustomers().get(0);
			final List<Statement> statements = new ArrayList<Statement>();
			try {

				Account account = customer.getAccounts().get(0);
				Statement statement = service.getStatement(customer, account);
				assertTrue(statement.getTotal() > 0);
				statements.add(statement);
			} catch (Exception e) {

				e.printStackTrace();
				assertNotNull(null);
			}

			try {
				Account account = customer.getAccounts().get(1);
				Statement statement = service.getStatement(customer, account);
				assertTrue(statement.getTotal() > 0);
				statements.add(statement);
			} catch (Exception e) {

				e.printStackTrace();
				assertNotNull(null);
			}

			try {
				Account account = customer.getAccounts().get(2);
				Statement statement = service.getStatement(customer, account);
				assertTrue(statement.getTotal() > 0);
				statements.add(statement);
			} catch (Exception e) {

				e.printStackTrace();
				assertNotNull(null);
			}

			
			
			System.out.println(service.getStatementView(customer, statements));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	public void withdrawFundsTest() {
		try {
			Customer customer = this.bankService.getBankCustomers().get(0);
			Account account = customer.getAccounts().get(0);
			service.withdrawFromAccount(customer, account, 1000.00);

			// assertTrue(account.getTransactions().size() == 2);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}

}

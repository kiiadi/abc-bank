package com.abc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.builder.StatementBuilder;
import com.abc.builder.TransactionBuilder;
import com.abc.controller.AccountController;
import com.abc.controller.BankController;
import com.abc.controller.CustomerController;
import com.abc.controller.TransactionController;
import com.abc.model.Account;
import com.abc.model.Customer;
import com.abc.model.Statement;
import com.abc.model.Transaction;
import com.abc.util.InterestCalculator;

/**
 * A Service class that our application will call
 * for all things that a Customer can perform.
 * 
 * User Stories satisified:
 * 	A customer can open an account
 *	A customer can deposit / withdraw funds from an account
 *	A customer can request a statement that shows transactions and totals for each of their accounts
 *
 * 
 * @author erieed
 */
public class CustomerService {

	@Autowired
	final BankController bankController = null;
	@Autowired
	final AccountController accountController = null;
	@Autowired
	final CustomerController customerController = null;
	@Autowired
	final TransactionController transController = null;
	@Autowired
	final StatementBuilder statementBuilder = null;
	@Autowired
	final InterestCalculator interest = null;
	
	
	/**
	 * Create a Customer and open and Account
	 * 
	 * @param name - name of customer
	 * @param accountName - name of acccount
	 * @param type - type of account
	 * @return
	 * @throws Exception
	 */
	public Customer createCustomerAndOpenAccount(final String name,
			final String accountName, final int type) throws Exception {
		if (this.customerController != null && name != null
				&& accountName != null) {
			final Customer customer = this.customerController.add(name);
			this.openAcccount(customer, accountName, type);
			this.bankController.getBank().getCustomers().add(customer);
			return customer;
		} else {
			throw new Exception("Name cant be null");
		}
	}

	/**
	 * 
	 * Open and Account with an already existing customer
	 * 
	 * @param customer - the customer to service
	 * @param name - name of account
	 * @param type - type of account to open
	 * @throws Exception
	 */
	public void openAcccount(final Customer customer, final String name,
			final int type) throws Exception {
		if (customer != null && this.customerController != null && name != null
				&& this.customerController.contains(customer.getUid())) {
			final Account act = this.accountController.add(name, type);
			customerController.addAccount(customer.getUid(), act);
		} else {
			throw new Exception("Name cant be null");
		}
	}

	/**
	 * Deposit money for a Customer to an open Account 
	 * 
	 * @param customer - the customer 
	 * @param account - the account to deposit money
	 * @param amount - amount of deposit
	 * @throws Exception
	 */
	public void depositToAccount(final Customer customer,
			final Account account, final double amount) throws Exception {
		if (customer != null && account != null && amount > 0) {
			if (this.customerController.contains(customer.getUid())
					&& this.accountController.contains(account.getUid())) {
				if (customer.getAccounts().contains(account)) {
					final Account act = this.accountController.get(account
							.getUid());
					final Transaction trans = this.transController.add(amount,
							TransactionBuilder.DEPOSIT);
					act.getTransactions().add(trans);
				}
			} else {
				throw new Exception("No dice");
			}
		} else {
			throw new Exception("Issue adding deposit");
		}
	}
	
	/**
	 * Adds interest to Account as a Transaction
	 * 
	 * @param customer - a customer 
	 * @param account - the account to add interest too
	 * @param amount - amount of interest
	 * @throws Exception
	 */
	public void interestToAccount(final Customer customer,
			final Account account, final double amount) throws Exception {
		if (customer != null && account != null && amount > 0) {
			if (this.customerController.contains(customer.getUid())
					&& this.accountController.contains(account.getUid())) {
				if (customer.getAccounts().contains(account)) {
					final Account act = this.accountController.get(account
							.getUid());
					final Transaction trans = this.transController.add(amount,
							TransactionBuilder.INTEREST);
					act.getTransactions().add(trans);
				}
			} else {
				throw new Exception("No dice");
			}
		} else {
			throw new Exception("Issue adding deposit");
		}
	}

	/**
	 * Allows user to Withdraw money from an account
	 * 
	 * @param customer - a customer 
	 * @param account - account to withdraw from
	 * @param amount - amount of transaction
	 * @throws Exception
	 */
	public void withdrawFromAccount(final Customer customer,
			final Account account, final double amount) throws Exception {
		if (customer != null && account != null && amount > 0) {
			if (this.customerController.contains(customer.getUid())
					&& this.accountController.contains(account.getUid())) {
				if (customer.getAccounts().contains(account)) {
					final Account act = this.accountController.get(account
							.getUid());
					final Transaction trans = this.transController.add((amount * -1.00),
							TransactionBuilder.WITHDRAW);
					act.getTransactions().add(trans);
					
				}
			} else {
				throw new Exception("No dice");
			}
		} else {
			throw new Exception("Issue withdrawing");
		}
	}

	/**
	 * Allows a customer to get a statement on his account
	 * 
	 * @param customer - the customer asking for a statement
	 * @param account - account to get statement for
	 * @return
	 * @throws Exception
	 */
	public Statement getStatement(final Customer customer, final Account account)
			throws Exception {
		if (account != null && customer != null) {
			double total = this.getTotalAccountAmount(customer, account);
			return statementBuilder.account(account).total(total)
					.createStatement();

		} else {
			throw new NullPointerException("Customer and Acocunt Null");
		}
	}

	//helper method to get total amount from an account
	private double getTotalAccountAmount(final Customer customer,
			final Account account) throws Exception {
		if (customer != null && account != null
				&& this.accountController.contains(account.getUid())
				&& customer.getAccounts().contains(account)) {
			final List<Transaction> trans = account.getTransactions();
			double totalAmount = 0;
			for (Transaction tran : trans) {
				totalAmount = totalAmount + tran.getAmount();
			}
			return totalAmount;
		} else {
			throw new NullPointerException("Account Null");
		}
	}

	
	/**
	 * Customer interest earned for a given Account
	 * 
	 * @param customer - the customer 
	 * @param account - account to calculate interest
	 * @return
	 * @throws Exception
	 */
	public double totalInterestEarned(final Customer customer, final Account account) throws Exception {
		double amount = this.getTotalAccountAmount(customer, account);
		double totalamount = account.accept(interest, this.getTotalAccountAmount(customer, account));
		this.interestToAccount(customer, account, totalamount - amount);
		return totalamount;
		
	}
	
	/**
	 * A String view for Customer Statements
	 * 
	 * @param customer - statement view
	 * @param statements - all statements for a Customer
	 * @return - String of acccount name and total amount 
	 */
	public String getStatementView(final Customer customer, final List<Statement> statements) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("Statements for ABC Bank : ");
		buffer.append(customer.getName());
		buffer.append("___________________________");
		for(Statement statement:statements) {
			buffer.append("  ");
			buffer.append(statement.getAccount().getName());
			buffer.append(":  ");
			buffer.append(statement.getTotal());
			
		}
		return buffer.toString();
	}
}

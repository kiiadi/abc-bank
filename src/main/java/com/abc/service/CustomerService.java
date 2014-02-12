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

	public void withdrawFromAccount(final Customer customer,
			final Account account, final double amount) throws Exception {
		if (customer != null && account != null && amount > 0) {
			if (this.customerController.contains(customer.getUid())
					&& this.accountController.contains(account.getUid())) {
				if (customer.getAccounts().contains(account)) {
					final Account act = this.accountController.get(account
							.getUid());
					final Transaction trans = this.transController.add(amount,
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

	public double totalInterestEarned(final Customer customer, final Account account) throws Exception {
		double amount = this.getTotalAccountAmount(customer, account);
		double totalamount = account.accept(interest, this.getTotalAccountAmount(customer, account));
		this.interestToAccount(customer, account, totalamount - amount);
		return totalamount;
		
	}
	
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

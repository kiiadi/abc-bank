package com.abc.banking.statement;

import com.abc.banking.exception.BankingCriticalException;
import com.abc.banking.model.Bank;
import com.abc.banking.model.Customer;
import com.abc.banking.model.account.Account;
import com.abc.banking.statement.constants.StatementGeneratorConstants;
import com.abc.banking.transaction.Transaction;

/**
 * This class generates different types of statements when requested
 *
 */
public class StatementGenerator {

	/**
	 * returns the account level transactions details
	 * @param account
	 * @return
	 */
	public String generateStatement(Account account){

		StringBuilder statement = new StringBuilder(
				StatementGeneratorConstants.TRANSACTION_HEADING).append(
				StatementGeneratorConstants.NEWLINE).append(
				StatementGeneratorConstants.UNDERLINE)
				.append(StatementGeneratorConstants.NEWLINE);

		for (Transaction transaction : account.getTransactions()) {
			statement.append(transaction.getTransactionDate())
			.append(StatementGeneratorConstants.WHITESPACES)
			.append(transaction.getTransactionType())
			.append(StatementGeneratorConstants.WHITESPACES)
			.append(transaction.getTransactionDescription())
			.append(StatementGeneratorConstants.WHITESPACES)
			.append(transaction.getTransactionAmount())
			.append(StatementGeneratorConstants.NEWLINE)
			.append(StatementGeneratorConstants.NEWLINE);
		}

		return statement.toString();

	}

	/**
	 * returns all transactions and total balance for each account of a customer 
	 * @param customer
	 * @return
	 */
	public String generateStatement(Customer customer) {

		StringBuilder statement = new StringBuilder(StatementGeneratorConstants.CUSTOMER_STATEMENT_HEADING)
		.append(StatementGeneratorConstants.NEWLINE)
		.append(StatementGeneratorConstants.UNDERLINE)
		.append(StatementGeneratorConstants.NEWLINE)
		.append(StatementGeneratorConstants.CUSTOMER_NAME)
		.append(customer.getCustomerName())
		.append(StatementGeneratorConstants.NEWLINE);
		

		for (Account account : customer.getAccountList()) {
			statement.append(StatementGeneratorConstants.ACCOUNT_TYPE)
			 .append(account.toString())
			.append(StatementGeneratorConstants.WHITESPACES)
			.append(account.getAccountBalance())
			.append(StatementGeneratorConstants.NEWLINE)
			.append(StatementGeneratorConstants.NEWLINE)
			.append(generateStatement(account));

		}

		return statement.toString();

	}

	/**
	 * returns list of customers and number of  accounts they have
	 * @param bank
	 * @return
	 */
	public String generateStatement(Bank bank) {

		StringBuilder statement = new StringBuilder(StatementGeneratorConstants.BANK_HEADING)
		.append(StatementGeneratorConstants.NEWLINE)
		.append(StatementGeneratorConstants.UNDERLINE)
		.append(StatementGeneratorConstants.NEWLINE);

		for (Customer customer : bank.getCustomers()) {

			statement.append(StatementGeneratorConstants.CUSTOMER_NAME)
			.append(customer.getCustomerName())
			.append(StatementGeneratorConstants.WHITESPACES)
			.append(StatementGeneratorConstants.NUMBER_OF_ACCOUNTS)
			.append(customer.getNumberOfAccounts())
			.append(StatementGeneratorConstants.NEWLINE);

		}

		return statement.toString();

	}

	/**
	 * returns total interest paid by the bank on all accounts
	 * @param bank
	 * @return
	 * @throws BankingCriticalException
	 */
	public String generateBankInterestPaidStatement(Bank bank)
	throws BankingCriticalException {

		return new StringBuilder(
				StatementGeneratorConstants.TOTAL_INTEREST_PAID)
		.append(calculateTotalInterestPaid(bank)).toString();

	}

	private double calculateTotalInterestPaid(Bank bank) throws BankingCriticalException{

		double totalInterest = 0.0 ;

		for (Customer customer : bank.getCustomers()) {
			for (Account account : customer.getAccountList()){				
				totalInterest =totalInterest+ account.calculateInterest();
			}			
		}
		return totalInterest;
	}


}

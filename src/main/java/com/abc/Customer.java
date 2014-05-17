package com.abc;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;

/**
 * The customer class
 * 
 * @author Jeff
 * 
 */
public class Customer
	{

		private String			name;
		private List<Account>	accounts;

		/**
		 * Create a Customer object
		 * 
		 * @param name - the customer name
		 */
		public Customer(String name)
			{
				this.name = name;
				this.accounts = new ArrayList<Account>();
			}

		/**
		 * Get the name of this customer
		 * 
		 * @return the customer name
		 */
		public String getName()
			{
				return name;
			}

		/**
		 * Open an account for this customer
		 * 
		 * @param account - the account to open
		 * @return the customer object
		 */
		public Customer openAccount(Account account)
			{
				accounts.add(account);
				return this;
			}

		/**
		 * The number of accounts that this customer has.
		 * 
		 * @return the number of accounts for this customer
		 */
		public int getNumberOfAccounts()
			{
				return accounts.size();
			}

		/**
		 * Get the total interest that this customer has earned
		 * 
		 * @return the interest that this customer has earned
		 */
		public double totalInterestEarned()
			{
				double total = 0;
				for (Account a : accounts)
					total += a.interestEarned();
				return total;
			}

		/**
		 * Create a statement for this customer
		 * 
		 * @return a string representing the customers statement of account
		 */
		public String getStatement()
			{
				String statement = null;
				statement = "Statement for " + name + "\n";

				// begin totaling the balances for all of the accounts
				double total = 0.0;
				for (Account a : accounts)
					{ // each account's balance is on a new line
						statement += "\n" + statementForAccount(a) + "\n";
						total += a.sumTransactions();	// keep running total for
														// all accounts
					}
				statement += "\nTotal In All Accounts " + Utils.toDollars(total);
				return statement;
			}

		/**
		 * Create a statement for an individual account
		 * 
		 * @param a the account to create the statement for an account
		 * @return a string representing the statement for the account passed
		 *         in.
		 */
		private String statementForAccount(Account a) throws IllegalArgumentException
			{
				if( a == null )
					throw new IllegalArgumentException(
								"account cannot be null.");

				
				String s = "";

				// Translate to pretty account type
				switch (a.getType())
					{
					case CHECKING:
						s += "Checking Account\n";
						break;
					case SAVINGS:
						s += "Savings Account\n";
						break;
					case MAXISAVINGS:
						s += "Maxi Savings Account\n";
						break;
					}

				// Now total up all the transactions
				double total = 0.0;
				for (Transaction t : a.getTransactionList() )
					{
						s += "  " + (t.amount < 0 ? "withdrawal" : "deposit")
										+ " " + Utils.toDollars(t.amount) + "\n";
						total += t.amount;
					}
				
				s += "Total " + Utils.toDollars(total);
				return s;
			}

	}

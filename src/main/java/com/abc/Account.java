package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * a class representing a customer bank account.
 * 
 * @author Jeff
 * 
 */

public class Account
	{

		private final AccountType	accountType;
		private TransactionList transactions;

		/**
		 * Create an Account object
		 * 
		 * @param _type
		 *            - the account type to create
		 */
		public Account(com.abc.AccountType _type)
			{
				this.accountType = _type;
				this.transactions = new TransactionList(this);
			}

		/**
		 * Deposit funds into an existing account
		 * 
		 * @param amount
		 *            - the amount to deposit into the account
		 */
		public void deposit(double amount)
			{
				// if the amount is LE zero, then this should throw
				// an exception
				if (amount <= 0)
					{
						throw new IllegalArgumentException(
										"amount must be greater than zero");
					}
				// otherwise
				else
					{

						transactions.add(new DepositTransaction(this, amount));
					}
			}

		/**
		 * Withdraw funds from an existing account
		 * 
		 * @param amount
		 *            - the amount to withdraw from the account
		 */
		public void withdraw(double amount)
			{
				// if the amount of the withdrawal is LE zero, throw an
				// exception.
				if (amount <= 0)
					{
						throw new IllegalArgumentException(
										"amount must be greater than zero");
					}
				else
					// create a new transaction to record the amount of the
					// withdrawal
					{
						transactions.add(new WithdrawalTransaction(this, -amount));
					}
			}

		/**
		 * calculate interest on the existing balance in the account
		 * 
		 * @return the amount of interest earned
		 */
		public double interestEarned()
			{
				double amount = sumTransactions();
				switch (this.accountType)
					{
					case SAVINGS:
						if (amount <= 1000)
							return amount * 0.001;
						else
							return 1 + (amount - 1000) * 0.002;

					case MAXISAVINGS:
						if (amount <= 1000)
							return amount * 0.02;
						if (amount <= 2000)
							return 20 + (amount - 1000) * 0.05;
						return 70 + (amount - 2000) * 0.1;
					default:
						return amount * 0.001;
					}
			}

		/**
		 * sum the transactions for this account
		 * 
		 * @return the sum of all transactions
		 */
		public double sumTransactions()
			{
				double amount = 0.0;
				for (Transaction t : transactions)
					amount += t.amount;
				return amount;			}

		/**
		 * Return the account type of this account
		 * 
		 * @return the type defined for this account
		 * @see AccountType
		 */
		public AccountType getType()
			{
				return accountType;
			}
		
		public TransactionList getTransactionList()
		{
			return transactions;
		}

		
	}

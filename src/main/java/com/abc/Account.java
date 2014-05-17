package com.abc;

/**
 * a class representing a customer bank account.
 * 
 * @author Jeff
 * 
 */

public abstract class Account
	{

		private final AccountType	accountType;
		private TransactionList		transactions;

		/**
		 * Create an Account object
		 * 
		 * @param _type - the account type to create
		 */
		Account(com.abc.AccountType _type)
			{
				this.accountType = _type;
				this.transactions = new TransactionList(this);
			}

		/**
		 * Deposit funds into an existing account
		 * 
		 * @param amount - the amount to deposit into the account
		 */
		public void deposit(double amount) throws IllegalArgumentException
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
		 * @param amount - the amount to withdraw from the account
		 */
		public void withdraw(double amount) throws IllegalArgumentException
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
						transactions.add(new WithdrawalTransaction(this,
										-amount));
					}
			}

		
		/**
		 * Calculate interest on the account. Each subclass is responsible for creating a method of
		 * calculating interest.
		 * 
		 * @return the interest on the current balance
		 * 
		 */
		public abstract double calculateInterest();
		
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
				return amount;
			}

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

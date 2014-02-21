package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Account represents our Bank's customer account. Customer can deposit or withdraw from his Account
 * @author Prachi
 *
 */
public class Account {

	/**
	 * AccountType represented as an enum
	 */
	private AccountType accountType;

	/**
	 *To track customer's total account balance 
	 */
	private double totalAccountBalance;

	/**
	 *  To track customer's last withdrawal date  
	 */
	private Date lastWithdrawalDate;

	/**
	 * 	List of transactions in customer's account
	 */
	private List<Transaction> transactions;

	/**
	 * Initializes the account with its type
	 * @param accountType input account type 
	 */
	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	/**
	 * Deposit the input amount to Account
	 * @param amount input amount to deposit
	 * @return void 
	 */
	public void deposit(double amount) {
		deposit(amount,DateProvider.getInstance().now());
	}

	/**
	 * Withdraw the input amount from Account
	 * @param amount input amount to withdraw
	 * @return void 
	 */
	public void withdraw(double amount) {
		withdraw(amount, DateProvider.getInstance().now());
	}

	/**
	 * Overloaded the method to be able to test the MAX-SAVING calculate interest scenario with respect to lastWithdrawalDate.
	 * @param amount input amount to deposit
	 * @param depositDate deposit date 
	 * @return void 
	 */
	public void deposit(double amount, Date depositDate) {
		if (amount <= 0 || depositDate == null) {
			throw new IllegalArgumentException("Invalid Deposit Amount or Deposit date");
		} else {
			transactions.add(new Transaction(amount,depositDate));
			totalAccountBalance = totalAccountBalance+amount;
		}
	}

	/**
	 * Overloaded the method to be able to test the MAX-SAVING calculate interest scenario with respect to lastWithdrawalDate.
	 * @param amount input amount to deposit
	 * @param withdrawDate withdrawal date
	 * @return void 
	 */
	public void withdraw(double amount, Date withdrawDate) {
		if (amount <= 0 || amount > totalAccountBalance || withdrawDate == null ) {
			throw new IllegalArgumentException("Invalid Withdraw Amount or Withdraw date");
		} else {
			transactions.add(new Transaction(-amount,withdrawDate));
			totalAccountBalance = totalAccountBalance-amount;
			lastWithdrawalDate = withdrawDate;
		}
	}

	/**
	 * Calculate the interest for accounts based on it's type
	 * @return interest earned on customer account
	 */
	public double interestEarned() {

		switch(accountType){
		case  SAVINGS:
			return calculateSavings();
		case CHECKING:
			return calculateChecking();
		case MAXI_SAVINGS:
			return calculateMaxiSavingsInterest();   // Called the method implemented to calculate interest with new logic
		default:
			return totalAccountBalance * 0.001;
		}
	}

	/**
	 * Calculate the interest for savings account balance
	 * @return interest on savings account balance
	 */
	private double calculateSavings(){
		if (totalAccountBalance > 0) {
			if (totalAccountBalance <= 1000) {
				return totalAccountBalance * 0.001;
			}
			else {
				return (totalAccountBalance-1000) * 0.002;
			}
		}
		return totalAccountBalance;
	}

	/**
	 * Calculate the interest for Checking account
	 * @return interest on checking account
	 */
	private double calculateChecking(){
		if (totalAccountBalance > 0) {
			return totalAccountBalance * 0.001;
		}
		return totalAccountBalance;
	}

	/**
	 * Older way of calculating Maxi Savings account interest
	 * @return interest on maxi saving account
	 */
	@Deprecated
	private double calculateMaxiSavings(){
		if (totalAccountBalance <= 1000)
			return totalAccountBalance * 0.02;
		if (totalAccountBalance <= 2000)
			return 20 + (totalAccountBalance-1000) * 0.05;
		return 70 + (totalAccountBalance-2000) * 0.1;
	}


	/**
	 * Calculate the interest on Maxi Savings Account with modified interest calculation formula
	 * @return interest on  Maxi savings account
	 */
	public double calculateMaxiSavingsInterest() {

		boolean isWithdraw = true;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -10);
		Date cutOffDate = cal.getTime();

		// isWithdraw will be false if either no withdrawal exists or exist only before 10 days from current date.
		if (totalAccountBalance > 0) {
			if(lastWithdrawalDate == null || lastWithdrawalDate.before(cutOffDate)){
				isWithdraw =false;
			}

			if (isWithdraw) {
				return (totalAccountBalance * 0.0001);
			} else {
				return (totalAccountBalance * 0.05);
			}
		}
		return totalAccountBalance;
	}

	/**
	 * Transaction sum is correctly updated and stored in totalAccountBalance. 
	 * This way we totalAccountBalance is ready to use and does not need to be calculated 
	 * every time its needed. 
	 * @return
	 */
	@Deprecated
	public double sumTransactions() {
		double amount = 0.0;
		for (Transaction t: transactions)
			amount += t.getAmount();
		return amount;
	}

	/**
	 * Getter method for account type
	 * @return account type
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * Getter method for account balance
	 * @return total account balance
	 */
	public double getTotalAccountBalance() {
		return totalAccountBalance;
	}

	/**
	 * Getter method for transactions
	 * @return list of transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}
}

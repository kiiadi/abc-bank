package com.abc;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * User: mchernyak
 * Date: 2/22/14
 * Time: 2:11 PM
 */
public class SimpleReport {
	protected static NumberFormat usaFormat = NumberFormat.getCurrencyInstance(Locale.US);

	/**
	 * Generate simple customer statement
	 * @param customer customer to generate statement for
	 * @return string representing customer statement
	 */
	public static String customerStatement(Customer customer, Date asOfDate) {
		StringBuilder statement = new StringBuilder("Statement for ");
		statement.append(customer.getName()).append("\n");
		double total = 0.0;

		for (Account a : customer.getAccounts()) {
			statement.append("\n").append(SimpleReport.accountStatement(a, asOfDate)).append("\n");
			total += a.getBalance();
		}

		statement.append("\nTotal In All Accounts ");
		statement.append(usaFormat.format(total));
		return statement.toString();
	}

	/**
	 * Generate an account statement string
	 *
	 * @return String representing an account statement
	 */
	public static String accountStatement(Account account, Date asOfDate) {
		account.calculateBalanceAndInterest(asOfDate);

		// Start with pretty account type
		StringBuilder s = new StringBuilder(account.getTypeName()).append("\n");

		// List transactions
		for (Transaction t : account.getTransactions()) {
			s.append("  ").append(t.getAmount() < 0 ? "withdrawal" : "deposit").append(" ");
			s.append(usaFormat.format(Math.abs(t.getAmount()))).append("\n");
		}

		// Account accrued interest
		s.append("Accrued Interest ").append(usaFormat.format(account.getInterest())).append("\n");

		// Account balance
		s.append("Total ").append(usaFormat.format(account.getBalance()));

		return s.toString();
	}

	public static String bankCustomerSummary(Bank bank) {
		String summary = "Customer Summary";
		for (Customer c : bank.getCustomers())
			summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
		return summary;
	}

	//Make sure correct plural of word is created based on the number passed in:
	//If number passed in is 1 just return the word otherwise add an 's' at the end
	private static String format(int number, String word) {
		return number + " " + (number == 1 ? word : word + "s");
	}


}

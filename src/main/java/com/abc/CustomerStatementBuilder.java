package com.abc;

public class CustomerStatementBuilder implements iReportBuilder{

	public String build(Object object) {
		Customer customer = (Customer) object;
		StringBuilder statementBuilder = new StringBuilder();
		statementBuilder.append("Statement for " + customer.getName() + "\n");
		double total = BankConstants.ZERO_AMOUNT;
		for (Account account : customer.getAccounts()) {
			statementBuilder.append("\n" + statementForAccount(account) + "\n");
			total += account.getBalance();
		}

		statementBuilder.append("\nTotal In All Accounts "
				+ Utility.toDollars(total));
		return statementBuilder.toString();

	}
	
	String statementForAccount(Account account) {
		StringBuilder statementBuilder = new StringBuilder();
		statementBuilder.append(account.getAccountType().getDescription())
				.append("\n");
		for (Transaction transaction : account.getAllTransactions()) {
			statementBuilder.append(transaction.printTransaction());
		}
		statementBuilder.append("Total ").append(
				Utility.toDollars(account.getBalance()));
		return statementBuilder.toString();
	}

}

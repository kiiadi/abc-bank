package com.abc;

public class TransactionBuilder implements iReportBuilder {

	public String build(Object object) {
		Transaction transaction = (Transaction)object;
		StringBuilder statementBuilder = new StringBuilder();
		statementBuilder.append("  ").append(transaction.getTransactType()) .append(" ")
				.append(Utility.toDollars(transaction.getAmount())) .append( "\n");
		return statementBuilder.toString();
	}

}

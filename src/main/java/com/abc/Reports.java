package com.abc;

import static java.lang.Math.abs;

import java.util.List;

public class Reports {
	public static String customerSummary(List<Customer> customers) {
		return new BankCustomerSummary(customers).report();
	}
	
	public static String totalInterestPaidSummary(Bank bank) {
		return String.format("Total Interest Paid Summary: %.2f", bank.totalInterestPaid());
	}
	
	public static String customerStatement(Customer customer) {
		return new CustomerStatement(customer).report();
	}
}

class BankCustomerSummary {
	private List<Customer> customers;
	
	public BankCustomerSummary(List<Customer> customers) {
		this.customers = customers;
	}
	
	public String report() {
	    String summary = "Customer Summary";
	    for (Customer c : customers)
	        summary += String.format("\n - %s (%d %s)", c.getName(), c.getNumberOfAccounts(), formatAccountLabel(c.getNumberOfAccounts()));
	    return summary;
	}
	
	private String formatAccountLabel(int number) {
		return (number<=1) ? "account" : "accounts";
	}
}

class CustomerStatement {
	private Customer customer;
	//StringBuilder builder; for further refactoring
	
	public CustomerStatement(Customer customer) {
		this.customer = customer;
	}
	
	public String report() {
	  String statement = null;
	  statement = "Statement for " + customer.getName() + "\n";
	  double total = 0.0;
	  for (Account account : customer.getAccounts()) {
	      statement += "\n" + statementForAccount(account) + "\n";
	      total += account.sumTransactions();
	  }
	  statement += "\nTotal In All Accounts " + toDollars(total);
	  return statement;
	}
	
	private String statementForAccount(Account account) {
	  String s = account.getAccountType().getLabel()+" Account\n";
	
	  for (Transaction t : account.getTransactions()) {
	      s += String.format("  %s %s\n", 
	    		  (t.getAmount() < 0 ? "withdrawal" : "deposit"),
	    		  toDollars(t.getAmount()));
	  }
	  s += "Total " + toDollars(account.sumTransactions());
	  return s;
	}
	
	private String toDollars(double amount){
	  return String.format("$%,.2f", abs(amount));
	}
}

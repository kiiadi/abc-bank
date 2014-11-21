package com.abc;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Math.abs;

public class Customer {
	
	// We assume Name is unique or we should have ssn. Commented by Henry
	private final String name;
    private Map<String, Account> accounts;

    public Customer(String name) {
    	
    	if (name==null||name.isEmpty())
    		 throw new IllegalArgumentException("Customer has to have a name.");
        this.name = name;
        this.accounts = new LinkedHashMap<String, Account>();
    }
    
    public Account getAccount (String accountNumber) {
    	if(!accounts.containsKey(accountNumber))
    		throw new IllegalArgumentException("account " + accountNumber + " does not exsits");
    	return accounts.get(accountNumber);
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts.values())
            total += a.interestEarned();
        return total;
    }
    
    public String getStatement() {
        StringBuilder statement = new StringBuilder( "Statement for " + name + "\n");
        double total = 0.0;
        for (Account a : accounts.values()) {
            statement.append("\n").append(statementForAccount(a)).append("\n");
            total += a.sumTransactions();
        }
        statement = statement.append("\nTotal In All Accounts ").append(toDollars(total));
        return statement.toString();
    }
    
    private String statementForAccount(Account a) {
        StringBuilder s = new StringBuilder("");

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s.append("Checking Account\n");
                break;
            case Account.SAVINGS:
                s.append("Savings Account\n");
                break;
            case Account.MAXI_SAVINGS:
                s.append("Maxi Savings Account\n");
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            s.append("  ").append((t.amount < 0 ? "withdrawal" : "deposit")).append(" ").append(toDollars(t.amount)).append("\n");
            total += t.amount;
        }
        s.append("Total ").append(toDollars(total));
        return s.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
   	
}

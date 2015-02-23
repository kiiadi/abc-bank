package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public synchronized Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public synchronized int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * @param from
     * @param to
     * @param amount
     * @since 0.2
     */
    public void transfer(int fromIdx, int toIdx, double amount) {
    	Account fromAccount, toAccount;
    	synchronized (this) {
			fromAccount = accounts.get(fromIdx);
			toAccount = accounts.get(toIdx);
    	}
    	fromAccount.withdraw(amount);
    	toAccount.deposit(amount);
    }
    
    public double totalInterestEarned() {
        double total = 0;
        //Should consider to make local copy for account list for better performance.
        synchronized (this) {
        	for (Account a : accounts)
        		total += a.interestEarned();
        }
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        //Should consider to make local copy for account list for better performance.
        synchronized (this) {
	        for (Account a : accounts) {
	            statement += "\n" + statementForAccount(a) + "\n";
	            total += a.sumTransactions();
	        }
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s += "Checking Account\n";
                break;
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}

package com.abc;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public BigDecimal totalInterestEarned() {
    	BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts)
            total = total.add(a.interestEarned());
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total = total.add(a.sumTransactions());
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
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction t : a.getTransactions()) {
            s += "  " + (t.getAmount().compareTo(BigDecimal.ZERO) < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n";
            total = total.add(t.getAmount());
        }
        
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(BigDecimal d){
        return NumberFormat.getCurrencyInstance(Locale.US).format(d);
    }
    
    
    public synchronized boolean TransferBetweenAccounts(Account fromAccount, Account toAccount,
    		BigDecimal amount) throws Exception
	{
    	if(fromAccount.sumTransactions().compareTo(amount) < 0){
            throw new Exception("Not enough money in account # " +
            		fromAccount.getAccountNumber() + " to be transfered to account # " +
            		toAccount.getAccountNumber());    		
    	}
    	
    	fromAccount.withdraw(amount);
    	toAccount.deposit(amount);
    	
    	return true;
    	
	}
}

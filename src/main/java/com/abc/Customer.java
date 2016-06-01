package com.abc;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private Map<Integer, Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new HashMap<Integer, Account>();
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
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts.values()) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case CHECKING:
                s += "Checking Account\n";
                break;
            case SAVINGS:
                s += "Savings Account\n";
                break;
            case MAXI_SAVINGS:
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
    
    public void transferFunds(int fromAcNum, int toAcNum, double amountToTransfer) throws Exception {

    	// validate fromAcType and toAcType should not be same
    	if(fromAcNum == toAcNum)
    		throw new IllegalArgumentException("fromAc and toAc should not be same.");
    	
    	// validate amountToTransfer should be greater than 0
    	if(amountToTransfer <= 0 )
    		throw new IllegalArgumentException("amountToTransfer should be greater than 0.");
    	
    	// find the two accounts to transfer funds
    	Account fromAc	= accounts.get(fromAcNum);
    	Account toAc	= accounts.get(toAcNum);
    	
    	// validate accounts were found
    	if(fromAc == null)
    		throw new IllegalArgumentException(String.format("Account %d not found for transfer.", fromAc));

    	// validate accounts were found
    	if(toAc == null)
    		throw new IllegalArgumentException(String.format("Account %d not found for transfer.", toAc));
    	
    	// validate funds are available for transfer
    	if(fromAc.sumTransactions() < amountToTransfer)
    		throw new Exception("Insufficient funds for transfer.");
    	
    	// do fund transfer
    	// withdraw and deposit should be part of a atomic transaction.
    	fromAc.withdraw(amountToTransfer);
    	toAc.deposit(amountToTransfer);
    	
    }
}

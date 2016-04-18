package com.abc;

import java.util.Vector;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;
    
    /*Used a vector to keep account withdrawals and deposit functions thread safe*/
    public Customer(String name) {
        this.name = name;
        this.accounts = new Vector<Account>();
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

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("Statement for " + name + "\n");
        double total = 0.0;
        for (Account a : accounts) {
            statement.append("\n" + statementForAccount(a) + "\n");
            total += a.sumTransactions();
        }
        statement.append("\nTotal In All Accounts " + toDollars(total));
        return statement.toString();
    }
    
    public String transferBetweenAccounts(Account from, Account to, Double amount){
    	
    	StringBuilder s= new StringBuilder();
    	
    	if(amount <= 0 ){
    		throw new IllegalArgumentException("Amount withdrawn should be greater than zero");
    	}else if(amount >= from.sumTransactions()){
    		throw new IllegalArgumentException("Amount withdrawn should not be greater than existing balance");
    	}
    	
    	synchronized(this){
    		from.withdraw(amount);
    		to.deposit(amount);
    	}
    	
    	return s.append("Amount: "+amount+
    					" withdrawn from "+from.getAccountNumber()
    					+" account and deposited to "+to.getAccountNumber()
    					+" account by "+this.name).toString();
        
    }
    
    
    
    private String statementForAccount(Account a) {
        StringBuilder s = new StringBuilder();
        
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
            //put in case default as well
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s.append("  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n");
            total += t.getAmount();
        }
        s.append("Total " + toDollars(total));
        return s.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
    public List<Account> getAccounts(){
    	return accounts;
    }
}

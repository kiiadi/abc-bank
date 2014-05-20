package com.abc;

import static java.lang.Math.abs;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.AccountType;
import com.abc.interfaces.CustomerDetail;

public class Customer implements CustomerDetail{
    private String name;
    private Map<Object, AccountDetail> m_accounts;

    public Customer(String name) {
        this.name = name;
        this.m_accounts = new LinkedHashMap<Object, AccountDetail>();
    }
    
    public Map<Object, AccountDetail>getAccounts(){
    	return m_accounts;
    }
    
	public AccountDetail createAccount(int accountType){
		Account ac = new Account(accountType);
		getAccounts().put(accountType, ac);		
		return ac;
	}
	
	

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        m_accounts.put(account.getAccountType()+"", account);
        return this;
    }

    

    public double totalInterestEarned() {
        double total = 0;
        for (AccountDetail a : m_accounts.values())
            total += a.getBalacne();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (AccountDetail a : m_accounts.values()) {
            statement += "\n" + statementForAccount((Account) a) + "\n";
            total += a.getBalacne();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case AccountType.CHECKING:
                s += "Checking Account\n";
                break;
            case AccountType.SAVINGS:
                s += "Savings Account\n";
                break;
            case AccountType.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.m_transactions) {
            s += "  " + (t.getTransactionAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getTransactionAmount()) + "\n";
            total += t.getTransactionAmount();
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }


}

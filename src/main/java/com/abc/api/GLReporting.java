package com.abc.api;

import static java.lang.Math.abs;

import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.AccountType;
import com.abc.interfaces.CustomerDetail;
import com.abc.interfaces.GLReportingApi;
import com.abc.interfaces.GeneralLedgerApi;
import com.abc.persistance.Account;
import com.abc.persistance.Transaction;

public class GLReporting implements GLReportingApi {
		
	public StringBuilder getCustomerSummary(GeneralLedgerApi glApi) {
		StringBuilder sb = new StringBuilder("Customer Summary");

		for (CustomerDetail c : glApi.getBankDetail().getCustomers().values()) {
			String fm = String.format("\n - %s (%d account%s)", c.getName(), c.getAccounts().size(), c.getAccounts().size()==1?"":"s");
			sb.append(fm);
		}
		return sb;
	}
	

	//TODO: need to change to stringbuilder and optimize this code - senthil
    public String getCustomerStatement(CustomerDetail customer) {
        String statement = null;
        statement = "Statement for " + customer.getName() + "\n";
        double total = 0.0;
        for (AccountDetail a : customer.getAccounts().values()) {
            statement += "\n" + statementForAccount((Account) a) + "\n";
            total += a.getBalance();
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

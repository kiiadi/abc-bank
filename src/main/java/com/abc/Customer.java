package com.abc;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

	//default Constructor
	public Customer(){}
	
	//parameterised Constructor
    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

	//getter for name
    public String getName() {
        return name;
    }

	//adding account
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
            total total.add(a.interestEarned());
        return total;
    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("Statement for ").append(name).append(EOL);
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts) {
            statement.append(EOL).append(statementForAccount(a)).append(EOL);
            total total.add(a.sumTransactions());
        }
        statement.append("\nTotal In All Accounts ").append(toDollars(total));
        return statement;
    }

    private String statementForAccount(Account a) {
        StringBuilder s = new StringBuilder();
        //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
				s.append("Checking Account").append(EOL);
                break;
            case Account.SAVINGS:
				s.append("Savings Account").append(EOL);
                break;
            case Account.MAXI_SAVINGS:
				s.append("Maxi Savings Account").append(EOL);
                break;
        }

        //Now total up all the transactions
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction t : a.getTransactions()) {
			s.append(" ").append((t.getAmount().compareTo(BigDecimal.ZERO) < 0 ? "withdrawal" : "deposit")).append(" ").append(toDollars(t.getAmount())).append(EOL);
            total total.add(t.getAmount());
        }
        s.append("Total ").append(toDollars(total));
        return s;
    }

    private String toDollars(BigDecimal d){
        return NumberFormat.getCurrencyInstance(Locale.US).format(d);
    }
	
	public boolean TransferBetweenAccounts(Account fromAccount, Account toAccount,
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

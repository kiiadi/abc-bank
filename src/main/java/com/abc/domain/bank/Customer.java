package com.abc.domain.bank;

import java.util.ArrayList;
import java.util.List;

import com.abc.domain.account.Account;
import com.abc.domain.statement.CustomerStatement;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.Clock;

public class Customer {
	
	private String name;

	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public Customer openAccount(Account account) {
		accounts.add(account);
		return this;
	}

	public String getName() {
		return name;
	}
	
	public List<Account> allAccounts() {
		return accounts;
	}
	
	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public Money totalInterest() {
		Money total = Money.dollars(0);
		for (Account each : accounts) {
			total = total.plus(each.interest());
		}
		return total;
	}

	public Money totalBalance() {
		Money total = Money.dollars(0);
		for (Account each : accounts) {
			total = total.plus(each.balance());
		}
		return total;
	}
	
	public String showStatement() {
		return new CustomerStatement(this).generate();
	}
	
	public void deposit(Money amount, Account to, Clock clock) {
	    to.deposit(amount, clock.now());
	}
	
	public void withdraw(Money amount, Account from, Clock clock) {
	    from.withdraw(amount, clock.now());
	}
	
	public void transfer(Money amount, Account from, Account to, Clock clock) {
	    from.withdraw(amount, clock.now());
	    to.deposit(amount, clock.now());
	}
	
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Customer)) {
            return false;
        }

        Customer other = (Customer) object;
        
        return getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "customer: " + getName();
    }
}
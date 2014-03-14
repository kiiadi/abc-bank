package com.abc.domain.account;

import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.TimePoint;

public class Entry {
	
	private Money amount;
	private TimePoint whenBooked;
	
	private EntryType entryType;
	
	public Entry(Money amount, TimePoint timePoint) {
		this.amount = amount;
		this.whenBooked = timePoint;
		this.entryType = (amount.isLessThan(Money.dollars(0))) ? EntryType.Withdrawal : EntryType.Deposit;
	}
	
	public Money amount() {
		return amount;
	}
	
	public TimePoint whenBooked() {
		return whenBooked;
	}
	
	public String name() {
		return entryType.getName();
	}
	
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Entry)) {
            return false;
        }

        Entry other = (Entry) object;
        return amount().equals(other.amount()) && whenBooked().equals(other.whenBooked());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + amount().hashCode();
        result = 37 * result + whenBooked().hashCode();

        return result;
    }
    
	@Override
	public String toString() {
		return "amount: " + amount + ", booked at: " + whenBooked;
	}
	
	enum EntryType {
		
		Deposit("deposit"), Withdrawal("withdrawal");
		
		private EntryType(String name) {
			this.name = name;
		}
		
		private String name;
		
		String getName() {
			return name;
		}
	}
}

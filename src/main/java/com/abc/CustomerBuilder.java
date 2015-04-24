package com.abc;

public class CustomerBuilder {
	public Customer build(String customerName) {
		return new Customer(SeqProvider.getInstance().nextCustomerId(), customerName);
	}
}

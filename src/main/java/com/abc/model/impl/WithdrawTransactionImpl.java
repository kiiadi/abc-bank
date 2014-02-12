package com.abc.model.impl;

import java.util.Date;


public class WithdrawTransactionImpl extends AbstractTransactionImpl {

	public WithdrawTransactionImpl(double amount, long uid, Date date) {
		super(amount, uid, date);
	}

}

package com.abc.client;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.SavingsAccount;
import com.abc.model.Bank;
import com.abc.model.Customer;
import com.abc.transaction.TransactionType;

public class BankClient {

	public BankClient() {
	}
	
	public static void main(String[] args) {
		Bank bank = new Bank();
		try {
			Customer customer1 = new Customer("111-11-1111","Jane","Doe");
			bank.addCustomer(customer1);
			System.out.println ( "Successfully Added customer1. "+customer1);
			Account account11 = new CheckingAccount("11");
			account11.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
			account11.processTransaction(new BigDecimal(10000.00D), TransactionType.DEPOSIT);
			customer1.openAccount(account11);
			
			Account account12 = new SavingsAccount("12");
			account11.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/03/01"));
			account12.processTransaction(new BigDecimal(11234.23D), TransactionType.DEPOSIT);
			customer1.openAccount(account12);
			
			System.out.println("Successfully Added 2 Accounts to Customer1... ");
			
			account11.processTransaction(new BigDecimal(2399.99D),TransactionType.WITHDRAW);
			
			System.out.println("Sucessfully Debited Account11 by $2399.99.. Updated Customer Accounts...");
			
			account11.processTransferTransaction(new BigDecimal(1000.00D), TransactionType.TRANSFER, account12);
			account12.processTransferTransaction(new BigDecimal(1000.00D), TransactionType.TRANSFER, account11);
			
			System.out.println(bank.getCustomerSummary());

			System.out.println(customer1.getCustomerStatement());
			
			System.out.println(bank.getTotalInterestPaidStatement());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

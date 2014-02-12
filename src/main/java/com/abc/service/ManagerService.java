package com.abc.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.builder.ReportBuilder;
import com.abc.controller.CustomerController;
import com.abc.controller.TransactionController;
import com.abc.model.Customer;
import com.abc.model.Report;
import com.abc.model.Statement;
import com.abc.model.Transaction;
import com.abc.model.impl.InterestTransactionImpl;

public class ManagerService {

	@Autowired
	final TransactionController transController = null;
	@Autowired
	final CustomerController customerController = null;
	@Autowired
	final ReportBuilder reportBuilder = null;
	
	public Report getCustomerReport() {
		final Collection<Customer> customers = customerController.getAll();
		final Map<String, Double> data = new HashMap<String, Double>();
		
		for(Customer customer:customers) {
			Double size = new Double(customer.getAccounts().size());
			data.put(customer.getName(), size);
		}
		
		return reportBuilder.map(data).createReport();
	}
	
	public Report getInterestReport() {
		final Map<String, Double> data = new HashMap<String, Double>();
		final Collection<Transaction> accounts = transController.getAll();
		double amount = 0;
		for(Transaction account:accounts) {
			//could overwrite methods on objects or use visitor pattern
			if(account instanceof InterestTransactionImpl) {
				amount = amount + account.getAmount();
			}
		}	
		data.put("All Account Interest :", amount);
		return reportBuilder.map(data).createReport();
	}
	
	
	public String getReportView(final Report report) {
		final StringBuffer buffer = new StringBuffer();
		final Map<String, Double> data = report.getInformation();
		
		final Collection<String> name = data.keySet();
		buffer.append("Report for ABC Bank : ");
		for(String names : name) {
			buffer.append("  ");
			buffer.append(names);
			buffer.append(" = ");
			buffer.append(data.get(names));
		}
		
		
		return buffer.toString();
	}
}

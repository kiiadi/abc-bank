package com.abc.api;

import com.abc.interfaces.CustomerDetail;
import com.abc.interfaces.GLReportingApi;
import com.abc.interfaces.GeneralLedgerApi;

public class GLReporting implements GLReportingApi {
		
	public StringBuilder getCustomerSummary(GeneralLedgerApi glApi) {
		StringBuilder sb = new StringBuilder("Customer Summary");

		for (CustomerDetail c : glApi.getBankDetail().getCustomers().values()) {
			String fm = String.format("\n - %s (%d account%s)", c.getName(), c.getAccounts().size(), c.getAccounts().size()==1?"":"s");
			sb.append(fm);
		}
		return sb;
	}
}

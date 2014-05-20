package com.abc.interfaces;

public interface GLReportingApi {

	StringBuilder getCustomerSummary(GeneralLedgerApi generalLedgerApi);
	String getCustomerStatement(CustomerDetail customer);
}

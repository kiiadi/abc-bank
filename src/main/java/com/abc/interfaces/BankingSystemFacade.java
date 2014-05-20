package com.abc.interfaces;

public interface BankingSystemFacade {
		
	GeneralLedgerApi getGeneralLedgerApi(String entityCode);
	 GLReportingApi getGLReportingApi();
	
}

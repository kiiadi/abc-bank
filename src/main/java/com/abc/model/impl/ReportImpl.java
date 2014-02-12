package com.abc.model.impl;

import java.util.Map;

import com.abc.model.Report;

public class ReportImpl implements Report {

	private Map<String, Double> data;
	
	public ReportImpl(Map<String, Double> data) {
		this.data = data;
	}
	public Map<String, Double> getInformation() {
		return data;
	}

}

package com.abc.builder;

import java.util.Map;

import com.abc.model.Report;
import com.abc.model.impl.ReportImpl;

public class ReportBuilder {
	private Map<String, Double> data;
	
	public ReportBuilder() {
	}
	
    public ReportBuilder(final Map<String, Double> data) 
    {
    	this.data = data;
    }

    public ReportBuilder map(final Map<String, Double> data)
    {
       this.data = data;
       return this;
    }
     
    public Report createReport()
    {
       return new ReportImpl(this.data);
    }
}
